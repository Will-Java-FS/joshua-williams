package com.revature.controllers;

import com.revature.models.Collector;

import com.revature.models.Card;
import com.revature.services.CollectorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class CollectorController {


    CollectorService collectorService;

    @Autowired
    public CollectorController(CollectorService collectorService) {
        this.collectorService = collectorService;
    }

    @Getter
    private static class Login {
        private String username;
        private String password;
        public Login(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    @GetMapping(value = "/test")
    public String test() {
        return "Test";
    }

    //    - As a user, I can create an account to hold my Items
    @PostMapping(value = "collector")
    public ResponseEntity<?> createCollector(@RequestBody Collector newCollector) {
        if (newCollector.getUsername().isEmpty() || Objects.equals(newCollector.getUsername(), "") || newCollector.getPassword().length() < 4) {
            return new ResponseEntity<>("Invalid Username or Password", HttpStatus.BAD_REQUEST);
        }
        try {
            Collector collector = collectorService.register(newCollector);
            return new ResponseEntity<>(collector, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Username already exists.", HttpStatus.BAD_REQUEST);
        }
    }

    //    - As a user, I can login to my account (which is stored in the database)
    @GetMapping(value = "collector")
    public ResponseEntity<Collector> login(@RequestBody Login login) {
        Optional<Collector> collector = collectorService.login(login.getUsername(), login.getPassword());
        return collector.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    /*
    //    - As a user, I can view the Items associated with my account
    @GetMapping("{collectorId}/cards")
    public List<Card> getAllCards(@PathVariable Long collectorId) {
        return collectorService.getAllCards();
    }

     */
}
