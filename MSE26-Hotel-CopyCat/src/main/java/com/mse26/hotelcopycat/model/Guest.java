package com.mse26.hotelcopycat.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "guests")
public class Guest {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Column(name = "last_name")
    private String lastName;
}
