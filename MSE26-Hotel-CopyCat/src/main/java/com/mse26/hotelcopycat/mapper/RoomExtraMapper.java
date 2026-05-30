package com.mse26.hotelcopycat.mapper;

import com.mse26.hotelcopycat.api.dtos.v1.RoomExtraResponseDto;
import com.mse26.hotelcopycat.model.RoomExtra;
import org.springframework.stereotype.Component;

@Component
public class RoomExtraMapper {

    public RoomExtraResponseDto toResponseDto(RoomExtra roomExtra) {
        return RoomExtraResponseDto.builder()
                .name(roomExtra.getName())
                .iconPath(roomExtra.getIconPath())
                .build();
    }
}
