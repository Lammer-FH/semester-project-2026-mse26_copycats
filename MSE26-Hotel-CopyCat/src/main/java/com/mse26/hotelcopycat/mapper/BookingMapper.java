package com.mse26.hotelcopycat.mapper;

import com.mse26.hotelcopycat.api.dtos.v1.BookingRequestDto;
import com.mse26.hotelcopycat.api.dtos.v1.BookingResponseDto;
import com.mse26.hotelcopycat.enums.BookingStatus;
import com.mse26.hotelcopycat.model.Booking;
import com.mse26.hotelcopycat.model.Guest;
import com.mse26.hotelcopycat.model.Room;
import com.mse26.hotelcopycat.model.RoomType;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class BookingMapper {
    public Booking toEntity(
            BookingRequestDto dto,
            Room room,
            BookingStatus status
    ){
        Booking booking = new Booking();

        booking.setRoom(room);
        booking.setCheckIn(dto.getCheckIn());
        booking.setCheckOut(dto.getCheckOut());
        booking.setBreakfast(dto.getBreakfast());
        booking.setStatus(status);
        booking.setCreatedAt(Instant.now());

        return booking;
    }

    public BookingResponseDto toResponseDto(
            Booking booking
    ){
        return BookingResponseDto.builder()
                .id(booking.getId())
                .roomType(booking.getRoom().getRoomType())
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
