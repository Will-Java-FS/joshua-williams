package com.revature.models;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number", updatable = false)
    private long number;

    @Column(nullable = false)
    private String name;

    @Column
    private String type;

    @Column(columnDefinition = "smallint CHECK (health>= 0)")
    private short health;

}
