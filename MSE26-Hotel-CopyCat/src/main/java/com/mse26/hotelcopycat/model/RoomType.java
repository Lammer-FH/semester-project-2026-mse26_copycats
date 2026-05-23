package com.mse26.hotelcopycat.model;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "room_types")
public class RoomType {
    @Getter
    @Id
    @Column(name = "id")
    private int id;

    @Getter
    @Column(name = "name")
    private String name;

    @Getter
    @Column(name = "description")
    private String description;

    @Getter
    @Column(name = "image_path")
    private String imagePath;

    @Getter
    @OneToMany(mappedBy = "roomType")
    private Set<Room> rooms;

    @Getter
    @ManyToMany
    @JoinTable(
            name = "room_type_room_extras",
            joinColumns = @JoinColumn(name = "id_room_types"),
            inverseJoinColumns = @JoinColumn(name = "id_room_extras")
    )
    private Set<RoomExtra> roomExtras;

    public RoomType() {}
}
