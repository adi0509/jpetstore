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
public class ItemController {
    
    @Autowired
    ItemRepository itemRepository;

	@Autowired
    InventoryRepository inventoryRepository;

    @GetMapping("/item/{itemId}")
	public ResponseEntity<Item> getCategoryById(@PathVariable("itemId") String itemId) {
		List<Item> itemData = itemRepository.findByItemId(itemId);

		if (itemData.size()>0) {
			return new ResponseEntity<>(itemData.get(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

// this route will check it the item is in stock or not
	@GetMapping("/item/stock/{itemId}")
	public ResponseEntity<Boolean> isItemInStock(@PathVariable("itemId") String itemId) {
		List<Inventory> inventoryData = inventoryRepository.findByItemId(itemId);

		if (inventoryData.size()>0) {
			return new ResponseEntity<>(inventoryData.get(0).getQuantity()>0, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/item/product/{productId}")
	public ResponseEntity<List<Item>> getItemListByProductId(@PathVariable("productId") String productId) {
		List<Item> itemData = itemRepository.findItemByProductId(productId);

		if (itemData.size()>0) {
			return new ResponseEntity<>(itemData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
