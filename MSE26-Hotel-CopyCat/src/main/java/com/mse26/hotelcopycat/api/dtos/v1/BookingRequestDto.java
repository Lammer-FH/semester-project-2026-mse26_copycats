package com.mse26.hotelcopycat.api.dtos.v1;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookingRequestDto(Integer roomTypeId,
                                @NotNull
                                @DateTimeFormat(pattern = "yyyy-MM-dd")
                                LocalDate checkIn,
                                @NotNull
                                @DateTimeFormat(pattern = "yyyy-MM-dd")
                                LocalDate checkOut,
                                @NotNull
                                @NotBlank
                                String firstName,
                                @NotNull
                                @NotBlank
                                String lastName,
                                @NotNull
                                @NotBlank
                                @Email
                                String email,
                                @NotNull
                                @NotBlank
                                String confirmEmail,
                                @NotNull
                                Boolean breakfast) {
}
