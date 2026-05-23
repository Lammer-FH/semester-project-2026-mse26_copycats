package com.mse26.hotelcopycat.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;

import java.util.Set;

@Entity(name = "room_extras")
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
