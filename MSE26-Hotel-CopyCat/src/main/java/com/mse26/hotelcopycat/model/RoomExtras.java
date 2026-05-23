package com.mse26.hotelcopycat.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "room_extras")
public class RoomExtras {
    @Getter
    @Id
    @Column(name = "id")
    private int id;

    @Getter
    @Column(name = "name")
    private String name;

    @Getter
    @Column(name = "icon_path")
    private String iconPath;

    @Getter
    @ManyToMany(mappedBy = "roomExtras")
    private Set<RoomType> roomTypes;

    public RoomExtras() {}
}
