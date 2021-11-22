package com.bezkoder.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bezkoder.spring.datajpa.model.Category;


import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    List<Category> findByCatId(String catId);
}
