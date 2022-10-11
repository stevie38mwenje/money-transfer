package com.example.moneytransfer.service;

import com.example.moneytransfer.domain.*;
import com.example.moneytransfer.exception.AccountNotFoundException;
import com.example.moneytransfer.repo.AccountRepo;
import com.example.moneytransfer.repo.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@Service
public class AccountServiceImpl implements AccountService{

    private static final String REQUEST_SUCCESSFULLY_COMPLETED = "Request Successfully completed";
    Date date = new Date();
    @Autowired
    private final AccountRepo accountRepo;
    @Autowired
    private final TransactionRepository transactionRepository;

//    @Autowired
//    private final AccountStatementRepository statementRepository;

    public AccountServiceImpl(AccountRepo accountRepo, TransactionRepository transactionRepository) {
        this.accountRepo = accountRepo;
        this.transactionRepository = transactionRepository;
//        this.statementRepository = statementRepository;
    }


    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Transaction sendMoney(TransfersRequest transferBalanceRequest) throws AccessException {
//        TransfersRequest transfersRequest = TransfersRequest.builder()
//                .amount()
//
//
//                .build();


        String fromAccountNumber = String.valueOf(transferBalanceRequest.getFromAccount());
        String toAccountNumber = String.valueOf(transferBalanceRequest.getToAccount());
        BigDecimal transactionAmount = transferBalanceRequest.getAmount();
        Account fromAccount = accountRepo.findByAccountNumberEquals(fromAccountNumber);
        Account toAccount = accountRepo.findByAccountNumberEquals(toAccountNumber);
        //validate to and from account
        if(fromAccount==null&&toAccount==null){
            throw new AccountNotFoundException("Account not valid");
        }

        else if(fromAccount.getBalance().compareTo(BigDecimal.ONE) == 1
                && fromAccount.getBalance().compareTo(transactionAmount) == 1
        ){
            fromAccount.setBalance(fromAccount.getBalance().subtract(transactionAmount));
            accountRepo.save(fromAccount);
            toAccount.setBalance(toAccount.getBalance().add(transactionAmount));
            accountRepo.save(toAccount);
            var ref = generateRandom(10);





//            Transaction transaction = new Transaction(0L,fromAccountNumber,transactionAmount,new Timestamp(System.currentTimeMillis()));
            Transaction transaction = Transaction.builder()
                    .accountNumber(fromAccountNumber)
                    .transactionAmount(transactionAmount)
                    .transactionDateTime(new Timestamp(System.currentTimeMillis()))
                    .transactionRef(String.valueOf(ref))

                    .build();
            transactionRepository.save(transaction);
            return transaction;
        }
        return null;
    }

    public static long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }


//    @Override
//    public AccountStatement getStatement(String accountNumber) {
//        var account = accountRepo.findByAccountNumberEquals(accountNumber);
//        if(account==null) throw new AccountNotFoundException("account not found");
//        else
//        return statementRepository.findByAccountNumber(accountNumber);
//    }

    @Override
    public Account createAccount(AccountDto accountDto) {
        Account account = Account.builder()
                .createdAt(date)
                .accountNumber(accountDto.getAccountNumber())
                .statusEnum(accountDto.getStatus())
                .balance(accountDto.getBalance())
                .build();
        return accountRepo.save(account);
    }

    @Override
    public Optional<Account> findById(Long id){
        return accountRepo.findById(id);
    }

    @Override
    public void deposit(Transaction transaction) {
        Transaction depositTransaction = Transaction.builder()
                .transactionRef(UUID.randomUUID().toString())
                .transactionDateTime(Timestamp.valueOf(LocalDateTime.now()))
                .transactionAmount(transaction.getTransactionAmount())
                .accountNumber(transaction.getAccountNumber())
                .build();
        transactionRepository.save(depositTransaction);
    }


//    private String transationRefGenerator(String account){
//        return null;
//
//    }
}
