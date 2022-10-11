package com.example.moneytransfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransfersRequest {
    private Account fromAccount;
    private Account toAccount;
    private BigDecimal amount;
    private Date createdAt;
}
