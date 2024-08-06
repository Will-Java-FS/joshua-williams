package com.revature.controllers;

import com.revature.models.Collector;

import com.revature.services.CollectorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value = "collector")
    public ResponseEntity<Collector> login(@RequestBody Login login) {
        Optional<Collector> collector = collectorService.login(login.getUsername(), login.getPassword());
        return collector.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

}
