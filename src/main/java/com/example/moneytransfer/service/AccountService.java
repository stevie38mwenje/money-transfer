package com.example.moneytransfer.service;

import com.example.moneytransfer.domain.*;
import org.springframework.expression.AccessException;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAll();

    Transaction sendMoney(TransfersRequest transferBalanceRequest) throws AccessException;
//    AccountStatement getStatement(String accountNumber);

    Account createAccount(AccountDto accountDto);

   Optional<Account> findById(Long id);

    void deposit(Transaction transaction);
}
