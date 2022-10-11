package com.example.moneytransfer.repo;

import com.example.moneytransfer.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
    Account findByAccountNumberEquals(String fromAccountNumber);

}
