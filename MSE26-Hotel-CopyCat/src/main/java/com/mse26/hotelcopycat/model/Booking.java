package com.mse26.hotelcopycat.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
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
    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @Getter
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Getter
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @Getter
    @Column(name = "breakfast", nullable = false)
    private boolean breakfast;

    @Getter
    @Column(name = "status", nullable = false)
    private String status;

    @Getter
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    public Booking() {}

    public Booking(
            Room room,
            Guest guest,
            LocalDate checkIn,
            LocalDate checkOut,
            boolean breakfast,
            String status,
            Instant createdAt
    ) {
        this.room = room;
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.breakfast = breakfast;
        this.status = status;
        this.createdAt = createdAt;
    }
}
