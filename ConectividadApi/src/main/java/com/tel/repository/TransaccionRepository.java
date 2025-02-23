package com.tel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tel.model.Transaction;
public interface TransaccionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    
   
}
