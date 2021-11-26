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
import com.bezkoder.spring.datajpa.model.Keyword;
import com.bezkoder.spring.datajpa.model.Product;
import com.bezkoder.spring.datajpa.model.Supplier;
import com.bezkoder.spring.datajpa.repository.CategoryRepository;
import com.bezkoder.spring.datajpa.repository.InventoryRepository;
import com.bezkoder.spring.datajpa.repository.ItemRepository;
import com.bezkoder.spring.datajpa.repository.ProductRepository;
import com.bezkoder.spring.datajpa.repository.SupplierRepository;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") String productId) {
		List<Product> productData = productRepository.findByProductId(productId);
                                                    

		if (productData.size()>0) {
			return new ResponseEntity<>(productData.get(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/product/category/{category}")
	public ResponseEntity<List<Product>> getProductListByCategory(@PathVariable("category") String category) {
		List<Product> productData = productRepository.findProductByCategory(category);

		if (productData.size()>0) {
			return new ResponseEntity<>(productData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/product/search")
	public ResponseEntity<List<Product>> searchProductByKeyword(@RequestBody Keyword requestBody) {
		List<Product> productData = productRepository.findByNameContaining(requestBody.getKeyword());
		if (productData.size()>0) {
			return new ResponseEntity<>(productData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
