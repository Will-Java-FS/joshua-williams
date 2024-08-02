package com.revature.controllers;


import com.revature.models.Card;

import com.revature.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    // - As a user, I can delete a Item by its ID (HINT: Use Path Params to select a Item by its ID)
    @DeleteMapping("/card/{number}")
    public ResponseEntity<Optional<Card>> deleteCard(@PathVariable long number) {
        Optional<Card> card = cardService.deleteCard(number);
        if (card.isPresent()) {
            return new ResponseEntity<>(card, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
