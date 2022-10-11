package com.example.moneytransfer.controller;

import com.example.moneytransfer.domain.*;
import com.example.moneytransfer.exception.AccountDataException;
import com.example.moneytransfer.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1")
public class MoneyTransferController {
    private final static Logger logger = LoggerFactory.getLogger(MoneyTransferController.class);

    @Autowired
    private final AccountService accountService;

    public MoneyTransferController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping(value ="/send-money",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Transaction> sendMoney(TransfersRequest transfersRequest) throws AccessException {
        logger.info("Sending money: {}",transfersRequest);
        var res = accountService.sendMoney(transfersRequest);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @PostMapping(value ="/account",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Account> createAccount(AccountDto accountDto) throws AccountDataException{
        logger.info("creating account: {}",accountDto);
        return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
    }
    //deposit to an account
    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private  void deposit(@RequestBody Transaction transaction){
        accountService.deposit(transaction);

    }

    @GetMapping("account")
    private ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<>(accountService.findAll(),HttpStatus.OK);
    }

    @GetMapping("account/{id}")
    private ResponseEntity<Optional<Account>> getAccount(@PathVariable("id") Long id){
        var res = accountService.findById(id);
        HttpStatus status = res != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

//    @GetMapping("account/statement/{accountNumber}")
//    private ResponseEntity<AccountStatement> getAccountStatement(@PathVariable("accountNumber") String accountNumber){
//        var res = accountService.getStatement(accountNumber);
//        HttpStatus status = res != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
//        return new ResponseEntity<>(res,HttpStatus.OK);
//    }









}
