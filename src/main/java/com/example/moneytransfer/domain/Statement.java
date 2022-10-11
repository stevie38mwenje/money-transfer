package com.example.moneytransfer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statement {
    @Id
    private Long id;
    @ManyToOne
    private Account accountId;
    private BigDecimal currentBalance;
    private LocalDate date;
}
