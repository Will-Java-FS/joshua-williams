package com.revature.repositories;

import com.revature.models.Collector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CollectorRepo extends JpaRepository<Collector, Integer> {

    Optional<Collector> findByUsername(String username);

    Optional<Collector> findByUsernameAndPassword(String userName, String password);

}
