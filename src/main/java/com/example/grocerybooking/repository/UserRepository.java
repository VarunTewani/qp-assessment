package com.example.grocerybooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.grocerybooking.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
