package com.revature.controllers;

import com.revature.models.*;
import com.revature.repositories.CardRepo;
import com.revature.services.CardService;
import com.revature.services.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
public class CardController {

    public CardService cardService;
    public CollectorService collectorService;

    public CardRepo cardRepo;

    @Autowired
    public CardController(CardService cardService, CollectorService collectorService) {
        this.collectorService = collectorService;
        this.cardService = cardService;
    }

    @PostMapping("card")
    public ResponseEntity<Card> createCard(@RequestBody Card card)
    {
        if (card.getCollectorId() != null) {
            Collector collector = collectorService.findById(card.getCollectorId());
            if (collector != null) {
                card.setCollector(collector);
            } else {
                return ResponseEntity.badRequest().body(null); // Handle case where collectorId is invalid
            }
        }

        Card createdCard = cardService.createCard(card);

        return ResponseEntity.ok(card);

    }

    @GetMapping("card")
    public List<Card> getAllCards()
    {

        return cardService.getAllCards();
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

    @DeleteMapping("/card/{number}")
    public ResponseEntity<Optional<Card>> deleteCard(@PathVariable int number) {

        Optional<Card> card = cardService.deleteCard(number);

        if (card.isPresent()) {

            return new ResponseEntity<>(card, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/card/collector/{collectorId}")
    public ResponseEntity<List<Card>> getCardsByCollectorId(@PathVariable Integer collectorId) {

        List<Card> cards = cardService.getCardsByCollectorId(collectorId);

        if (cards.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

}