package com.example.service;

import com.example.entity.Account;
import com.example.exception.InvalidAccountException;
import com.example.exception.UnauthorizedException;

public interface IAccountService {
    /**
     * Register a new account
     * 
     * @param account the account to register
     * @return the registered account
     * @throws InvalidAccountException if the account has an empty username, a password less than 4 characters long, or the username already exists
     */
    Account register(Account account) throws InvalidAccountException;

    /**
     * Login to an account
     * 
     * @param account the account to login to
     * @return the account logged into
     * @throws UnauthorizedException if the password is incorrect for the username of the account
     */
    Account login(Account account) throws UnauthorizedException;
}
