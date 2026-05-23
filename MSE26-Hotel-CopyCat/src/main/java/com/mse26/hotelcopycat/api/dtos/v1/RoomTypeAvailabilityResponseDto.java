package com.mse26.hotelcopycat.api.dtos.v1;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class RoomTypeAvailabilityResponseDto {
    Integer roomTypeId;
    LocalDate checkIn;
    LocalDate checkOut;
    boolean available;
}
