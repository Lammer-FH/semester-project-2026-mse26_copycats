package com.mse26.hotelcopycat.service;
import com.mse26.hotelcopycat.api.dtos.v1.RoomTypeResponseDto;
import com.mse26.hotelcopycat.mapper.RoomTypeMapper;
import com.mse26.hotelcopycat.repository.RoomTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomDataService {
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    public RoomDataService(RoomTypeRepository roomTypeRepository, RoomTypeMapper roomTypeMapper) {
        this.roomTypeRepository = roomTypeRepository;
        this.roomTypeMapper = roomTypeMapper;
    }

    public Page<RoomTypeResponseDto> findAll(Pageable pageable){
        return roomTypeRepository.findAllBy(pageable).map(roomTypeMapper::toResponseDto);
    }

    public Optional<RoomTypeResponseDto> findById(int id){
        return roomTypeRepository.findWithRoomAndRoomExtrasById(id).map(roomTypeMapper::toResponseDto);
    }
}
