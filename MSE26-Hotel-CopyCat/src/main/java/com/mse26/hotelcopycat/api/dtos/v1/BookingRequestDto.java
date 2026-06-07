package com.mse26.hotelcopycat.api.dtos.v1;

import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class BookingRequestDto {
    Integer roomTypeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkOut;
    String firstName;
    String lastName;
    String email;
    String confirmEmail;
    Boolean breakfast;
}
