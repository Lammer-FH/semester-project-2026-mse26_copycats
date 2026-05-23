package com.mse26.hotelcopycat.api.dtos.v1;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoomExtraResponseDto {
    String name;
    String iconPath;
}
