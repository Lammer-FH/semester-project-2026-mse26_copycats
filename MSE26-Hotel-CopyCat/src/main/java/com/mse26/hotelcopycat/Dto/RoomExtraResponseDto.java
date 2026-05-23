package com.mse26.hotelcopycat.Dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoomExtraResponseDto {
    String name;
    String iconPath;
}
