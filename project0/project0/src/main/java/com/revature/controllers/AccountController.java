package com.revature.controllers;

import com.revature.models.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    Account a;


    //    - As a user, I can create an account to hold my Items
    @PutMapping("/account")
    public Account createAccount() {
        return a;
    }

    //    - As a user, I can login to my account (which is stored in the database)
    @GetMapping("/account")
    public Account login() {
        return a;
    }

    //    - As a user, I can view the Items associated with my account
    @GetMapping("/account/pokemon")
    public Account getAllPokemon() {
        return a;
    }
}
