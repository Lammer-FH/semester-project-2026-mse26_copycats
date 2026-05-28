package com.mse26.hotelcopycat.api.dtos.v1;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class RoomTypeResponseDto {
    Integer id;
    String name;
    String description;
    String imagePath;
    List<RoomExtraResponseDto> extras;
}
