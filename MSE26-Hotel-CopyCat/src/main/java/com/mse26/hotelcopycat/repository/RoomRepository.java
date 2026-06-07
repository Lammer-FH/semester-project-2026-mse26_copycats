package com.mse26.hotelcopycat.repository;

import com.mse26.hotelcopycat.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    long countByRoomType_Id(Integer roomTypeId);
}
