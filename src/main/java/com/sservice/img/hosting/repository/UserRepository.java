package com.sservice.img.hosting.repository;

import com.sservice.img.hosting.dto.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface  UserRepository extends  JpaRepository<UserData, Long> {
    boolean existsByUsername(String username);
    Optional<UserData> findByUsername(String username);
}
