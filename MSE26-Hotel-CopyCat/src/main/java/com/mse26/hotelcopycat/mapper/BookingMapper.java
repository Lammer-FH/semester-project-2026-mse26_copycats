package com.mse26.hotelcopycat.mapper;

import com.mse26.hotelcopycat.api.dtos.v1.BookingRequestDto;
import com.mse26.hotelcopycat.api.dtos.v1.BookingResponseDto;
import com.mse26.hotelcopycat.model.Booking;
import com.mse26.hotelcopycat.model.Guest;
import com.mse26.hotelcopycat.model.Room;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class BookingMapper {
    private final RoomTypeMapper roomTypeMapper;

    public BookingMapper(RoomTypeMapper roomTypeMapper){
        this.roomTypeMapper = roomTypeMapper;
    }

    public Booking toEntity(
            BookingRequestDto dto,
            Room room,
            Guest guest
    ){
        Booking booking = new Booking(
                room,
                guest,
                dto.checkIn(),
                dto.checkOut(),
                dto.breakfast(),
                "CONFIRMED",
                Instant.now());

        return booking;
    }

    public BookingResponseDto toResponseDto(
            Booking booking
    ){
        return BookingResponseDto.builder()
                .id(booking.getId())
                .roomType(roomTypeMapper.toResponseDto(booking.getRoom().getRoomType()))
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .firstName(booking.getGuest().getFirstName())
                .lastName(booking.getGuest().getLastName())
                .email(booking.getGuest().getEmail())
                .breakfast(booking.isBreakfast())
                .status(booking.getStatus())
                .createdAt(booking.getCreatedAt())
                .build();
    }
}
