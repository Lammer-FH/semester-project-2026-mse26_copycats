package com.mse26.hotelcopycat.api.v1;

import com.mse26.hotelcopycat.api.dtos.v1.BookingRequestDto;
import com.mse26.hotelcopycat.api.dtos.v1.BookingResponseDto;
import com.mse26.hotelcopycat.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service){
        this.service = service;
    }

    @PostMapping
    public BookingResponseDto createBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto){
        return service.createBooking(bookingRequestDto);
    }
}
