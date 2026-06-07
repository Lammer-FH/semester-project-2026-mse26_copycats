package com.mse26.hotelcopycat.api.dtos.v1;

import com.mse26.hotelcopycat.enums.BookingStatus;
import com.mse26.hotelcopycat.model.RoomType;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;

@Value
@Builder
public class BookingResponseDto {
    Integer id;
    RoomType roomType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkOut;
    String firstName;
    String lastName;
    String email;
    Boolean breakfast;
    BookingStatus status;
    Instant createdAt;
}
