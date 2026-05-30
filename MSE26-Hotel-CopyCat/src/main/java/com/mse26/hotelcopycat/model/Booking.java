package com.mse26.hotelcopycat.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Getter
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Getter
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    public Booking() {}
}
