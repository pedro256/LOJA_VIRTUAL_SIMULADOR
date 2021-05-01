package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.models.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
