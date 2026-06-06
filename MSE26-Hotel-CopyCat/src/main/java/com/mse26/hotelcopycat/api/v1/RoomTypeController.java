package com.mse26.hotelcopycat.api.v1;

import com.mse26.hotelcopycat.api.dtos.v1.RoomTypeAvailabilityResponseDto;
import com.mse26.hotelcopycat.api.dtos.v1.RoomTypeResponseDto;
import com.mse26.hotelcopycat.service.RoomTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/room-types")
public class RoomTypeController {
    private final RoomTypeService service;

    public RoomTypeController(RoomTypeService service) {
        this.service = service;
    }

    @GetMapping
    public Page<RoomTypeResponseDto> getAllRoomTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public RoomTypeResponseDto getRoomTypeById(@PathVariable int id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Room type with id " + id + " not found"));
    }

    @GetMapping("/{id}/availability")
    public RoomTypeAvailabilityResponseDto getAvailability(
            @PathVariable int id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut
    ) {
        return service.getAvailability(id, checkIn, checkOut);
    }
}
