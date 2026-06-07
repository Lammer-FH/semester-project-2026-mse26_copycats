package com.mse26.hotelcopycat.model;

import com.mse26.hotelcopycat.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @Setter
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST) // automatically persist Guest Entity as well
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @Getter
    @Setter
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Getter
    @Setter
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @Getter
    @Setter
    @Column(name = "breakfast", nullable = false)
    private boolean breakfast;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status;

    @Getter
    @Setter
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    public Booking() {}
}
