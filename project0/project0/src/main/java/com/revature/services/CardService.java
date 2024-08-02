package com.revature.services;

import com.revature.models.Card;
import com.revature.repositories.CardRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CardService {

    CardRepo cardRepo;

    @Autowired
    public CardService(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    public Card createCard(Card card)

    {

        return cardRepo.save(card);

    }

    public List<Card> getAllCards()

    {

        return cardRepo.findAll();

    }

    public Card getCardById(Integer number)
    {

        Optional<Card> optionalMessage = cardRepo.findById(number);
        return optionalMessage.orElse(null);

    }

    public Card updateCard(Integer number, Card card)

    {


        if (cardRepo.existsById(number)) {


            if (card.getName().isEmpty()) {
                throw new IllegalArgumentException("Please enter a Card Name");
            }

            if (card.getName().length() > 255) {
                throw new IllegalArgumentException("Message to long");
            }


            return cardRepo.save(card);
        }
        return null;

    }
    public Optional<Card> deleteCard(int id) {
        Optional<Card> card = cardRepo.findById(id);
        if (card.isPresent()) {
            cardRepo.deleteById(id);
        }
        return card;
    }
}
