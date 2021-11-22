package com.bezkoder.spring.datajpa.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bezkoder.spring.datajpa.model.BannerData;

public interface BannerDataRepository extends JpaRepository<BannerData, Long> {

}
