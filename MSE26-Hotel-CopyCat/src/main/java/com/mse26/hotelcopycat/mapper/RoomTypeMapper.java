package com.mse26.hotelcopycat.mapper;

import com.mse26.hotelcopycat.api.dtos.v1.RoomTypeResponseDto;
import com.mse26.hotelcopycat.model.RoomExtra;
import com.mse26.hotelcopycat.model.RoomType;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class RoomTypeMapper {
    private final RoomExtraMapper roomExtraMapper;

    public RoomTypeMapper(RoomExtraMapper roomExtraMapper) {
        this.roomExtraMapper = roomExtraMapper;
    }

    public RoomTypeResponseDto toResponseDto(RoomType roomType) {
        return RoomTypeResponseDto.builder()
                .id(roomType.getId())
                .name(roomType.getName())
                .description(roomType.getDescription())
                .imagePath(roomType.getImagePath())
                .extras(roomType.getRoomExtras() == null
                        ? List.of()
                        : roomType.getRoomExtras().stream()
                        .sorted(Comparator.comparingInt(RoomExtra::getId))
                        .map(roomExtraMapper::toResponseDto)
                        .toList())
                .build();
    }
}
