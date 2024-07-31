package com.revature.services;

import com.revature.models.Account;
import com.revature.repositories.AccountRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    /**
     * Create a new account in JPA repository
     * @param newAccount
     * @return the account if created
     */
    public Account register(Account newAccount) {
        Optional<Account> existingAccount = accountRepo.findByUsername(newAccount.getUsername());
        if (existingAccount.isPresent()) {
            throw new DataIntegrityViolationException("Username already exists");
        }
        return accountRepo.save(newAccount);
    }
}
