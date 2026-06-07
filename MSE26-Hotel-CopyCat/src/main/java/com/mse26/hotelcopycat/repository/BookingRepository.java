package com.mse26.hotelcopycat.repository;

import com.mse26.hotelcopycat.model.Booking;
import com.mse26.hotelcopycat.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("""
            select count(distinct b.room.id)
            from Booking b
            where b.room.roomType.id = :roomTypeId
              and b.checkIn < :checkOut
              and b.checkOut > :checkIn
            """)
    long countBookedRoomsForRoomTypeInPeriod(
            Integer roomTypeId,
            LocalDate checkOut,
            LocalDate checkIn
    );

    @Query("""
       select distinct b.room
       from Booking b
       where b.room.roomType.id = :roomTypeId
         and b.checkIn < :checkOut
         and b.checkOut > :checkIn
       """)
    List<Room> findBookedRoomsForRoomTypeInPeriod(
            Integer roomTypeId,
            LocalDate checkOut,
            LocalDate checkIn
    );
}
