package com.example.springdatarediscache.dao.repository;

import com.example.springdatarediscache.dao.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
