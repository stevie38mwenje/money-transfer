package com.example.moneytransfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link Account} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto implements Serializable {
    private BigDecimal balance;
    private StatusEnum status;
    private String accountNumber;
//    private UserDto owner;
}