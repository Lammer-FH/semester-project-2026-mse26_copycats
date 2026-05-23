package com.mse26.hotelcopycat.Mapper;

import com.mse26.hotelcopycat.Dto.RoomExtraResponseDto;
import com.mse26.hotelcopycat.Model.RoomExtras;
import org.springframework.stereotype.Component;

@Component
public class RoomExtraMapper {

    public RoomExtraResponseDto toResponseDto(RoomExtras roomExtras) {
        return RoomExtraResponseDto.builder()
                .name(roomExtras.getName())
                .iconPath(roomExtras.getIconPath())
                .build();
    }
}
