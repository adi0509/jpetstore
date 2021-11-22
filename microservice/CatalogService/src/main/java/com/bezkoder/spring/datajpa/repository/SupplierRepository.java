package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	// List<Signon> findByUsername(String username);
	// List<Signon> findByUsernameAndPassword(String username, String password);
}
