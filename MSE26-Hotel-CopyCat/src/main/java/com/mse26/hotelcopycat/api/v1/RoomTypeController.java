package com.mse26.hotelcopycat.api.v1;

import com.mse26.hotelcopycat.api.dtos.v1.RoomTypeResponseDto;
import com.mse26.hotelcopycat.service.RoomDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/room-types")
public class RoomTypeController {
    private final RoomDataService service;

    public RoomTypeController(RoomDataService service) {
        this.service = service;
    }

    @GetMapping
    public Page<RoomTypeResponseDto> getAllRoomTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<RoomTypeResponseDto> getRoomTypeById(@PathVariable int id) {
        return service.findById(id);
    }
}
