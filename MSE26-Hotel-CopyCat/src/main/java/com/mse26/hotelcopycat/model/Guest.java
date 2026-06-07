package com.mse26.hotelcopycat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Column(name = "email")
    private String email;

    public Guest() {}

    public Guest(
            String firstName,
            String lastName,
            String email
    ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
