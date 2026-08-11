package com.hub_entrprice.ltd.repository;

import com.hub_entrprice.ltd.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   User findByUsername(String username);

}

