package com.mse26.hotelcopycat.api.dtos.v1;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponseDto {
    int status;
    String error;
    String message;
}
