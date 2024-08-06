package com.revature.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.revature.models.Collector;
import com.revature.repositories.CollectorRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;


class CollectorServiceTest {

    @Mock
    private CollectorRepo collectorRepo;

    @InjectMocks
    private CollectorService collectorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_NewCollector() {
        Collector newCollector = new Collector();
        newCollector.setUsername("newUser");

        when(collectorRepo.findByUsername("newUser")).thenReturn(Optional.empty());
        when(collectorRepo.save(newCollector)).thenReturn(newCollector);

        Collector result = collectorService.register(newCollector);

        assertNotNull(result);
        assertEquals("newUser", result.getUsername());
        verify(collectorRepo, times(1)).findByUsername("newUser");
        verify(collectorRepo, times(1)).save(newCollector);
    }

    @Test
    void testRegister_ExistingCollector() {
        Collector newCollector = new Collector();
        newCollector.setUsername("existingUser");

        when(collectorRepo.findByUsername("existingUser")).thenReturn(Optional.of(new Collector()));

        assertThrows(DataIntegrityViolationException.class, () -> {
            collectorService.register(newCollector);
        });

        verify(collectorRepo, times(1)).findByUsername("existingUser");
        verify(collectorRepo, never()).save(newCollector);
    }

    @Test
    void testLogin_Success() {
        String username = "user";
        String password = "pass";
        Collector collector = new Collector();
        collector.setUsername(username);
        collector.setPassword(password);

        when(collectorRepo.findByUsernameAndPassword(username, password)).thenReturn(Optional.of(collector));

        Optional<Collector> result = collectorService.login(username, password);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        assertEquals(password, result.get().getPassword());
        verify(collectorRepo, times(1)).findByUsernameAndPassword(username, password);
    }

    @Test
    void testLogin_Failure() {
        String username = "user";
        String password = "wrongpass";

        when(collectorRepo.findByUsernameAndPassword(username, password)).thenReturn(Optional.empty());

        Optional<Collector> result = collectorService.login(username, password);

        assertFalse(result.isPresent());
        verify(collectorRepo, times(1)).findByUsernameAndPassword(username, password);
    }
}
