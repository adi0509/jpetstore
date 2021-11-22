package com.bezkoder.spring.datajpa.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bezkoder.spring.datajpa.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>  {
    // List<Profile> findByUsername(String username);
    List<Item> findByItemId(String itemId);
    List<Item> findItemByProductId(String productId);
}
