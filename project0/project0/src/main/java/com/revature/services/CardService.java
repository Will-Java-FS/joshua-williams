package com.revature.services;

import com.revature.models.Card;
import com.revature.repositories.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepo cardRepo;

    public Optional<Card> getCard(long id) {
        return cardRepo.findById(id);
    }

    public Optional<Card> deleteCard(long id) {
        Optional<Card> card = cardRepo.findById(id);
        if (card.isPresent()) {
            cardRepo.deleteById(id);
        }
        return card;
    }
}
