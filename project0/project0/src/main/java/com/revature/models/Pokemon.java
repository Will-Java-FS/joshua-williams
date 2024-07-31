package com.revature.models;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id", updatable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String type;

    @Column(columnDefinition = "smallint CHECK (hp>= 0)")
    private short hp;

    @Column
    private String attack;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public short getHp() {
        return hp;
    }

    public void setHp(short hp) {
        this.hp = hp;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return id == pokemon.id && hp == pokemon.hp && Objects.equals(name, pokemon.name) && Objects.equals(type, pokemon.type) && Objects.equals(attack, pokemon.attack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, hp, attack);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", hp=" + hp +
                ", attack='" + attack + '\'' +
                '}';
    }
}
