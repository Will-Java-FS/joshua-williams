package com.revature.services;

import com.revature.models.Collector;
import com.revature.repositories.CollectorRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CollectorService {

    CollectorRepo collectorRepo;

    @Autowired
    public CollectorService(CollectorRepo collectorRepo) {
        this.collectorRepo = collectorRepo;
    }


    public Collector register(Collector newCollector) {
        Optional<Collector> existingCollector = collectorRepo.findByUsername(newCollector.getUsername());
        if (existingCollector.isPresent()) {
            throw new DataIntegrityViolationException("Username already exists");
        }
        return collectorRepo.save(newCollector);
    }


    public Optional<Collector> login(String username, String password) {
        return collectorRepo.findByUsernameAndPassword(username, password);
    }

    public Collector findById(Integer id) {
        return collectorRepo.findById(id).orElse(null);
    }
}
