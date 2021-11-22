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

import com.bezkoder.spring.datajpa.model.Category;
import com.bezkoder.spring.datajpa.model.Inventory;
import com.bezkoder.spring.datajpa.model.Item;
import com.bezkoder.spring.datajpa.model.Product;
import com.bezkoder.spring.datajpa.model.Supplier;
import com.bezkoder.spring.datajpa.repository.CategoryRepository;
import com.bezkoder.spring.datajpa.repository.InventoryRepository;
import com.bezkoder.spring.datajpa.repository.ItemRepository;
import com.bezkoder.spring.datajpa.repository.ProductRepository;
import com.bezkoder.spring.datajpa.repository.SupplierRepository;



@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class InventoryController {
    
    @Autowired
    InventoryRepository inventoryRepository;

    @PostMapping("/inventory")
	public ResponseEntity<String> updateInventory(@RequestBody List<Inventory> inventoryList) {
		try{
			for(Inventory item: inventoryList){
				List<Inventory> itemList = inventoryRepository.findByItemId(item.getItemId());
				if(itemList.size()<1){
					//return false
					return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
				}
				Inventory item2 = itemList.get(0);

				System.out.println(item.getQuantity()+" "+ item.getItemId());
				
				item2.setQuantity(item2.getQuantity()-item.getQuantity());
				inventoryRepository.save(item2);
			}
			System.out.println(inventoryList.get(0).getQuantity()+" "+ inventoryList.get(0).getItemId());
			return new ResponseEntity<>("Success", HttpStatus.OK);		
		} catch(Exception ex){
			System.out.print("Exception occurred!!"+ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
}

/*
[
    {
        "itemId":"EST-1",
        "qty":"1"
    },
    {
        "itemId":"EST-4",
        "qty":"100"
    }
]

*/