package com.example.moneytransfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;
    private String transactionRef;
    private String accountNumber;
    private BigDecimal transactionAmount;
    private Timestamp transactionDateTime;
}
