package com.revature.controllers;

import com.revature.models.Card;
import com.revature.repositories.CardRepo;
import com.revature.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
public class CardController {

    public CardService cardService;

    public CardRepo cardRepo;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("card")
    public ResponseEntity<?> createCard(@RequestBody Card card)
    {
        if(card.getNumber()!= null)
        {
            if(cardService.getCardById(card.getNumber())!=null)
            {
                return new ResponseEntity<>("Card already exists", HttpStatus.CONFLICT);
            }
        }

        if(card.getName() == null || card.getName().isEmpty())
        {

            return ResponseEntity.badRequest().body("Enter a card name");

        }
        else if(card.getHealth() < 0)
        {
            return ResponseEntity.badRequest().body("Enter a health greater than 0");
        }
        else

        {

            Card createdCard = cardService.createCard(card);

        }
        return ResponseEntity.ok(card);
    }

    @GetMapping("card")
    public ResponseEntity<List<Card>> getAllCards()
    {


        List<Card> cards = cardService.getAllCards();

        if(cards.isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        else
        {
            return ResponseEntity.ok(cards);
        }

    }

    @GetMapping("card/{number}")
    public ResponseEntity<Card> getCardById(@PathVariable Integer number)
    {
        Card card = cardService.getCardById(number);
        if(card == null) {

            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(card);
    }

    @PatchMapping("card/{number}")
    public ResponseEntity<?> updateCard(@PathVariable Integer number, @RequestBody Card cardUpdate)
    {

        try{

            if(cardUpdate.getName().trim().isEmpty() || cardUpdate.getName().length() > 250)
            {

                return ResponseEntity.badRequest().body("Name cannot be empty");

            }

            Card cardUpdated = cardService.updateCard(number, cardUpdate);

            if(cardUpdated == null)
            {
                return ResponseEntity.badRequest().body("Could not update card");
            }

            return ResponseEntity.ok(1);


        } catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    // - As a user, I can delete a Item by its ID (HINT: Use Path Params to select a Item by its ID)
    @DeleteMapping("/card/{number}")
    public ResponseEntity<Optional<Card>> deleteCard(@PathVariable int number) {
        Optional<Card> card = cardService.deleteCard(number);
        if (card.isPresent()) {
            return new ResponseEntity<>(card, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}