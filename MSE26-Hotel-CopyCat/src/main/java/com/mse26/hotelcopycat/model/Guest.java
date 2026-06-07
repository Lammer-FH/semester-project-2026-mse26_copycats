package com.mse26.hotelcopycat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.print.Book;
import java.util.Set;

@Entity
@Table(name = "guests")
public class Guest {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @OneToMany(mappedBy = "guest")
    private Set<Booking> bookings;

    @Getter
    @Setter
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;
}
