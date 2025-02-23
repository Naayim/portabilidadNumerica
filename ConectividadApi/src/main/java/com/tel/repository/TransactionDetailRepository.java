package com.tel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tel.model.TransactionDetail;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long>, JpaSpecificationExecutor<TransactionDetail> {

}
