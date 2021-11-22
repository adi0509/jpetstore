package com.bezkoder.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.datajpa.model.Orders;
import com.bezkoder.spring.datajpa.model.OrderStatus;
import com.bezkoder.spring.datajpa.model.OrderItems;
import com.bezkoder.spring.datajpa.model.LineItem;
import com.bezkoder.spring.datajpa.model.Sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.bezkoder.spring.datajpa.repository.OrderRepository;
import com.bezkoder.spring.datajpa.repository.OrderStatusRepository;
import com.bezkoder.spring.datajpa.repository.LineitemRepository;
import com.bezkoder.spring.datajpa.repository.SequenceRepository;

import com.google.gson.Gson; 

@CrossOrigin(origins = "http://localhost:8080") 
@RestController
@RequestMapping("/api")
public class OrdersController {
    
    @Autowired
    OrderRepository orderRepository;

	@Autowired
	SequenceRepository sequenceRepository; 

	@Autowired
	OrderStatusRepository orderStatusRepository;

	@Autowired
	LineitemRepository lineitemRepository;

    @GetMapping("/order/{orderId}")
	public ResponseEntity<Orders> getOrderByOrderId(@RequestParam int orderId){	
		try{
			List<Orders> orderData = orderRepository.findByOrderId(orderId);
			return new ResponseEntity<>(orderData.get(0), HttpStatus.OK);
		} catch(Exception e){
			//thow error here
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return null;
	}

   @GetMapping("/order/user/{userId}")
	public ResponseEntity<List<Orders>> getAllOrderByUserId(@RequestParam String userId){	
		try{
			List<Orders> orderList = orderRepository.findOrdersByUserId(userId);
			
			return new ResponseEntity<>(orderList, HttpStatus.OK);
		} catch(Exception e){
			//thow error here
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return null;
	}

	//this function will return nextId to order route
	public int getNextId(String name){
		//query db
		Sequence sequence = sequenceRepository.findNextIdByName(name);
		//+1
		System.out.println("#1: ");
		System.out.println(" #1a "+ sequence.getName());
		System.out.println(" #1b "+ sequence.getNextId());
		int nextId = sequence.getNextId();
		System.out.println("#2");
		sequence.setNextId(nextId+1);
		System.out.println("#3");
		//update db
		sequenceRepository.save(sequence);
		System.out.println("#4");
		return nextId;
	}

	@GetMapping("/order/nextid")
	public ResponseEntity<Integer> signin() {
		try {
				Integer nextId = getNextId("ordernum");
				System.out.println("#5");
				return new ResponseEntity<>(nextId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/order/new")
	public ResponseEntity<String> placeNewOrder(@RequestBody OrderItems orderItems) {
		try {
			
			System.out.println("###0");
			orderItems.getOrders().setOrderId(getNextId("ordernum"));
			//process incoming lineitem array
			//List<LineItem>
			
			System.out.println("###1: "+orderItems.getLineItems());

			Double totalPrice = 0.0;
			for(LineItem lineItem:orderItems.getLineItems()){
				String itemId = lineItem.getItemId();
				Integer increment = lineItem.getQuantity();
				
				System.out.println("###1 Test: "+ lineItem.getQuantity() +" " + lineItem.getUnitprice());
				totalPrice += (lineItem.getQuantity()*Integer.parseInt(lineItem.getUnitprice()));
				// Map<String, Object> param = new HashMap<>(2);
				// param.put("itemId", itemId);
				// param.put("increment", increment);
				// itemMapper.updateInventoryQuantity(param);			    
			}
			//TODO: communicate to inventory microservice through REST API
			// send lineItem list to inventory route (/api/inventory)

			System.out.println("###2");
			System.out.println("The JSON representation of Object mobilePhone is ");    
        	System.out.println(new Gson().toJson(orderItems.getLineItems()));


			// orderItems.getLineItems().forEach(lineItem -> {
			// 	String itemId = lineItem.getItemId();
			// 	Integer increment = lineItem.getQuantity();
				
			// 	totalPrice += (lineItem.getQuantity()*Integer.parseInt(lineItem.getUnitPrice()));
			// 	// Map<String, Object> param = new HashMap<>(2);
			// 	// param.put("itemId", itemId);
			// 	// param.put("increment", increment);
			// 	// itemMapper.updateInventoryQuantity(param);

			// 	//TODO: communicate to inventory microservice through REST API
			    updateInventoryRequest2(orderItems.getLineItems(), "http://localhost:8080/api/inventory");
			//   });
			  						    
			orderItems.getOrders().setTotalPrice(String.valueOf(totalPrice));
			//after processing update order table
			System.out.println("###2.5: "+orderItems.getOrders().getOrderId());
			//Update Total Price


			//Then update order table
			Orders _order = orderRepository.save(orderItems.getOrders());
			
			System.out.println("###3");
			// orderMapper.insertOrder(order);
			// orderMapper.insertOrderStatus(order);
			
			// NOTE: try to understand the logic behing the orderstatus table, does 1 order will have only 1 record in orderstatus table or multiple records(equal to the number of items in that order)
			OrderStatus orderStatus = new OrderStatus(
				_order.getOrderId(),
				orderItems.getLineItems().size(),
				"P",
				_order.getOrderDate()
				);
			
				System.out.println("###4");
			orderStatusRepository.save(orderStatus);

			orderItems.getLineItems().forEach(lineItem -> {
				System.out.println("###4.5: "+lineItem.getItemId()+" "+_order.getOrderId());
				lineItem.setOrderId(_order.getOrderId());
				System.out.println("###4.6: "+lineItem.getItemId()+" "+lineItem.getOrderId());

				lineitemRepository.save(lineItem);
			// save updated lineitems to database
			// lineItemMapper.insertLineItem(lineItem);
			
			});



			
			return new ResponseEntity<>(_order.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("--------------------------------------------");
			System.out.println(e);
			System.out.println("--------------------------------------------");

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//NOTE: no longer in use
	public static void updateInventoryRequest(List<LineItem> listItems, String url) throws IOException{
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept", "application/json");
		//con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		String reqBody = new Gson().toJson(listItems).toString();
		System.out.println("--------------ReqBody:"+reqBody);
		os.write(reqBody.getBytes(), 0, reqBody.length());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}

	}

	public static void updateInventoryRequest2(List<LineItem> listItems, String Givenurl) throws IOException{
		URL url = new URL (Givenurl);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		String jsonInputString = new Gson().toJson(listItems).toString();
		try(OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);			
		}

		try(BufferedReader br = new BufferedReader(
			new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
		}

	}
}
/*


private static void sendPOST() throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}





new Orders(
					orderItems.getOrders().getOrderId(),
					orderItems.getOrders().getUserId() ,
					orderItems.getOrders().getOrderDate(),
					orderItems.getOrders().getShipAddr1(),
					orderItems.getOrders().getShipAddr2(),
					orderItems.getOrders().getShipCity(),
					orderItems.getOrders().getShipState(),
					orderItems.getOrders().getShipZip(),
					orderItems.getOrders().getShipCountry(),
					orderItems.getOrders().getBillAddr1(),
					orderItems.getOrders().getBillAddr2(),
					orderItems.getOrders().getBillCity(),
					orderItems.getOrders().getBillState(),
					orderItems.getOrders().getBillZip(),
					orderItems.getOrders().getBillCountry(),
					orderItems.getOrders().getCourier(),
					orderItems.getOrders().getTotalPrice(),
					orderItems.getOrders().getShipToFirstUserId(),
					orderItems.getOrders().getShipToLastUserId(),
					orderItems.getOrders().getCreditCard(),
					orderItems.getOrders().getExprDate(),
					orderItems.getOrders().getCardType(),
					orderItems.getOrders().getLocale(),
					)



					[{"orderId":1001,"linenum":1,"itemId":"NA","quantity":2,"unitprice":"500"},{"orderId":1001,"linenum":2,"itemId":"NA","quantity":2,"unitprice":"500"},{"orderId":1001,"linenum":3,"itemId":"NA","quantity":2,"unitprice":"500"}]

					[{"orderId":1001,"linenum":1,"itemId":"EST-1","quantity":2,"unitprice":"500"},{"orderId":1001,"linenum":1,"itemId":"EST-4","quantity":20,"unitprice":"500"}]

{

    "lineItems": [
        {
            "orderId": 1001,
            "linenum": 1,
            "itemId": "NA",
            "quantity": 2,
            "unitprice": "500"
        },
        {
            "orderId": 1001,
            "linenum": 2,
            "itemId": "NA",
            "quantity": 2,
            "unitprice": "500"
        },
        {
            "orderId": 1001,
            "linenum": 3,
            "itemId": "NA",
            "quantity": 2,
            "unitprice": "500"
        }
    ]
}


*/