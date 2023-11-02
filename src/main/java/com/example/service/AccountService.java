package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.InvalidAccountException;
import com.example.exception.UnauthorizedException;
import com.example.repository.AccountRepository;

@Service
public class AccountService implements IAccountService {
    private AccountRepository repo;

    public AccountService(AccountRepository repository) {
        repo = repository;
    }
    
    @Override
    public Account register(Account account) throws InvalidAccountException {
        /* CM: There are a lot of reasons to not like Spring Data.
         * Not being able to portably check for constraint violation is chief among them. */
        if (account.getUsername().length() == 0 || account.getPassword().length() < 4 || repo.existsByUsername(account.getUsername())) {
            throw new InvalidAccountException();
        }

        return repo.save(account);
    }

    @Override
    public Account login(Account account) throws UnauthorizedException {
        Account a = repo.findByUsernameAndPassword(account.getUsername(), account.getPassword());

        if (a == null) {
            throw new UnauthorizedException();
        }

        return a;
    }
}
