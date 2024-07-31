package com.revature.repositories;

import com.revature.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PokemonRepo extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByName(String name);
}
