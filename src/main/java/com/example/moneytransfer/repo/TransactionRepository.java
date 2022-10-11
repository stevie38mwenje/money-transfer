package com.example.moneytransfer.repo;

import com.example.moneytransfer.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
