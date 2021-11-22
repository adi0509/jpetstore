package com.bezkoder.spring.datajpa.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bezkoder.spring.datajpa.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>  {

}
