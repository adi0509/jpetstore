package com.bezkoder.spring.datajpa.model;

import java.util.List;


public class OrderItems {
    Orders orders;
    List<LineItem> lineItems;

    //empty constructor
    // public OrderItems(){}
    
    public OrderItems(
        int orderId, 
        String userId, 
        String orderdate,
        String shipAddr1,
        String shipAddr2,
        String shipcity,
        String shipstate,
        String shipzip,
        String shipcountry,
        String billaddr1,
        String billaddr2,
        String billcity,
        String billstate,
        String billzip,
        String billcountry,
        String courier,
        String totalprice,
        String billtofirstname,
        String billtolastname,
        String shiptofirstname,
        String shiptolastname,
        String creditcard,
        String exprdate,
        String cardtype,  
        String locale,
        List<LineItem> lineItems
      ){
          orders = new Orders(
                orderId, 
                userId, 
                orderdate,
                shipAddr1,
                shipAddr2,
                shipcity,
                shipstate,
                shipzip,
                shipcountry,
                billaddr1,
                billaddr2,
                billcity,
                billstate,
                billzip,
                billcountry,
                courier,
                totalprice,
                billtofirstname,
                billtolastname,
                shiptofirstname,
                shiptolastname,
                creditcard,
                exprdate,
                cardtype,  
                locale
          );

            this.lineItems = lineItems;

      }
    
    

    public Orders getOrders(){
        return this.orders;
    }
    
    public List<LineItem> getLineItems(){
        return this.lineItems;
    }
} 


// {
//     "orderId": "1",
//     "userId": "j2ee",
//     "orderdate": "2021/01/01",
//     "shipAddr1": "1",
//     "shipAddr2": "1",
//     "shipcity": "1",
//     "shipstate": "1",
//     "shipzip": "1",
//     "shipcountry": "1",
//     "billaddr1": "1",
//     "billaddr2": "1",
//     "billcity": "1",
//     "billstate": "1",
//     "billzip": "1",
//     "billcountry": "1",
//     "courier": "1",
//     "totalprice": "1",
//     "billtofirstname": "1",
//     "billtolastname": "1",
//     "shiptofirstname": "1",
//     "shiptolastname": "1",
//     "creditcard": "1",
//     "exprdate": "1",
//     "cardtype": "1",
//     "locale": "1",
//     "lineItems": [
//         {
//             "orderId": 1001,
//             "linenum": 1,
//             "itemId": "NA",
//             "quantity": 2,
//             "unitprice": "500"
//         },
//         {
//             "orderId": 1001,
//             "linenum": 2,
//             "itemId": "NA",
//             "quantity": 2,
//             "unitprice": "500"
//         },
//         {
//             "orderId": 1001,
//             "linenum": 3,
//             "itemId": "NA",
//             "quantity": 2,
//             "unitprice": "500"
//         }
//     ]
// }