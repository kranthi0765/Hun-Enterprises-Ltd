package com.hub_entrprice.ltd.entity;

import java.time.LocalDateTime;

import com.hub_entrprice.ltd.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "users", uniqueConstraints =@UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;
    
    private String password;
    
   @Column(unique = true)
    private String email;

    private String phoneNumber;

    private LocalDateTime createdOn=LocalDateTime.now();
    private LocalDateTime updatedOn=LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.JOBSEAKER;
   
}

