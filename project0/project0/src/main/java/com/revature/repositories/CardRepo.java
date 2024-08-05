package com.revature.repositories;

import com.revature.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Integer> {
    List<Card> findByName(String name);
    List<Card> findByCollectorId(Integer collectorId);
}
