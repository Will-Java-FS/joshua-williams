package com.revature.controllers;


import com.revature.models.Pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {

    @Autowired
    Pokemon p;

    // - As a user, I can delete a Item by its ID (HINT: Use Path Params to select a Item by its ID)
    @DeleteMapping("/pokemon")
    public Pokemon deletePokemon() {
        return p;
    }

}
