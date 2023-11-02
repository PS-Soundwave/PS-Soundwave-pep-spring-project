package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.entity.Account;

public interface AccountRepository extends Repository<Account, Integer> {
    Account save(Account account);

    // CM: Unused. Tests test that this interface is  repository by checking for method names rather than being assignable to Repository
    List<Account> findAll();

    boolean existsById(int id);

    boolean existsByUsername(String username);

    Account findByUsernameAndPassword(String username, String password);
}
