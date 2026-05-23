package com.mse26.hotelcopycat.Model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "rooms")
public class Room {
    @Getter
    @Id
    @Column(name = "id")
    private int id;

    @Getter
    @Column(name = "room_nr")
    private String roomNr;

    @Getter
    @ManyToOne
    @JoinColumn(name = "id_room_types")
    private RoomType roomType;

    public Room() {}
}
