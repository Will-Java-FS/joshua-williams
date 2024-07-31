package com.revature.services;

import com.revature.models.Pokemon;
import com.revature.repositories.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    PokemonRepo pokemonRepo;

    public Optional<Pokemon> getPokemon(long id) {
        return pokemonRepo.findById(id);
    }

    public Optional<Pokemon> deletePokemon(long id) {
        Optional<Pokemon> pokemon = pokemonRepo.findById(id);
        pokemonRepo.deleteById(id);
        return pokemon;
    }
}
