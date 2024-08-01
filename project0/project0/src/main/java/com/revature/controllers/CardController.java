package com.revature.controllers;


import com.revature.models.Card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    Card p;

    // - As a user, I can delete a Item by its ID (HINT: Use Path Params to select a Item by its ID)
    @DeleteMapping("/card")
    public Card deleteCard() {
        return p;
    }

}
