package com.mse26.hotelcopycat.service;
import com.mse26.hotelcopycat.api.dtos.v1.RoomTypeAvailabilityResponseDto;
import com.mse26.hotelcopycat.api.dtos.v1.RoomTypeResponseDto;
import com.mse26.hotelcopycat.mapper.RoomTypeMapper;
import com.mse26.hotelcopycat.repository.BookingRepository;
import com.mse26.hotelcopycat.repository.RoomRepository;
import com.mse26.hotelcopycat.repository.RoomTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class RoomTypeService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final BookingRepository bookingRepository;
    private final RoomTypeMapper roomTypeMapper;

    public RoomTypeService(
            RoomRepository roomRepository,
            RoomTypeRepository roomTypeRepository,
            BookingRepository bookingRepository,
            RoomTypeMapper roomTypeMapper
    ) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.bookingRepository = bookingRepository;
        this.roomTypeMapper = roomTypeMapper;
    }

    public Page<RoomTypeResponseDto> findAll(Pageable pageable){
        return roomTypeRepository.findAllBy(pageable).map(roomTypeMapper::toResponseDto);
    }

    public Optional<RoomTypeResponseDto> findById(int id){
        return roomTypeRepository.findWithRoomAndRoomExtrasById(id).map(roomTypeMapper::toResponseDto);
    }

    public RoomTypeAvailabilityResponseDto getAvailability(int roomTypeId, LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            throw new ResponseStatusException(BAD_REQUEST, "checkIn and checkOut are required");
        }
        if (!checkOut.isAfter(checkIn)) {
            throw new ResponseStatusException(BAD_REQUEST, "checkOut must be after checkIn");
        }
        if (roomTypeRepository.findById(roomTypeId).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Room type with id " + roomTypeId + " not found");
        }

        long totalRooms = roomRepository.countByRoomType_Id(roomTypeId);
        long bookedRooms = bookingRepository
                .countBookedRoomsForRoomTypeInPeriod(
                        roomTypeId,
                        checkOut,
                        checkIn
                );

        return RoomTypeAvailabilityResponseDto.builder()
                .roomTypeId(roomTypeId)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .available(totalRooms > bookedRooms)
                .build();
    }
}
