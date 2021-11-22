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
	}

	//this function will return nextId to order route
	public int getNextId(String name){
		Sequence sequence = sequenceRepository.findNextIdByName(name);
		int nextId = sequence.getNextId();
		sequence.setNextId(nextId+1);
		sequenceRepository.save(sequence);
		return nextId;
	}

	@GetMapping("/order/nextid")
	public ResponseEntity<Integer> signin() {
		try {
				Integer nextId = getNextId("ordernum");
				return new ResponseEntity<>(nextId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/order/new")
	public ResponseEntity<String> placeNewOrder(@RequestBody OrderItems orderItems) {
		try {
			
			orderItems.getOrders().setOrderId(getNextId("ordernum"));
			
			Double totalPrice = 0.0;
			for(LineItem lineItem:orderItems.getLineItems()){
				String itemId = lineItem.getItemId();
				Integer increment = lineItem.getQuantity();
				totalPrice += (lineItem.getQuantity()*Integer.parseInt(lineItem.getUnitprice()));		    
			}

			System.out.println("The JSON representation of LineItems: ");    
        	System.out.println(new Gson().toJson(orderItems.getLineItems()));

			//communicate to inventory microservice through REST API
		    updateInventoryRequest(orderItems.getLineItems(), "http://api-catalog:8082/api/inventory");
			  						    
			orderItems.getOrders().setTotalPrice(String.valueOf(totalPrice));

			//Then update order table
			Orders _order = orderRepository.save(orderItems.getOrders());
			
			// NOTE: try to understand the logic behing the orderstatus table, does 1 order will have only 1 record in orderstatus table or multiple records(equal to the number of items in that order)
			OrderStatus orderStatus = new OrderStatus(
				_order.getOrderId(),
				orderItems.getLineItems().size(),
				"P",
				_order.getOrderDate()
				);
			
			orderStatusRepository.save(orderStatus);

			orderItems.getLineItems().forEach(lineItem -> {
				lineItem.setOrderId(_order.getOrderId());
				lineitemRepository.save(lineItem);
			});



			
			return new ResponseEntity<>(_order.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static void updateInventoryRequest(List<LineItem> listItems, String Givenurl) throws IOException{
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