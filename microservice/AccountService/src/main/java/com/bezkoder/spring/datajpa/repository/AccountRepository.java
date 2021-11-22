package com.bezkoder.spring.datajpa.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bezkoder.spring.datajpa.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    // List<Account> findByUsername(String username);
}
