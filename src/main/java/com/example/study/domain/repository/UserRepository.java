package com.example.study.domain.repository;

import com.example.study.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByName(String name);
}
