package com.example.moneytransfer.repo;

import com.example.moneytransfer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
