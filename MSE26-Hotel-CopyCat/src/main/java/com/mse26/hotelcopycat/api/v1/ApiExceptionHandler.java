package com.mse26.hotelcopycat.api.v1;

import com.mse26.hotelcopycat.api.dtos.v1.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> handleResponseStatusException(ResponseStatusException exception) {
        HttpStatusCode statusCode = exception.getStatusCode();

        return ResponseEntity.status(statusCode)
                .body(buildErrorResponse(
                        statusCode.value(),
                        resolveReasonPhrase(statusCode),
                        exception.getReason()
                ));
    }

    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ErrorResponseDto> handleBadRequest(Exception exception) {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse(400, "Bad Request", exception.getMessage()));
    }

    private ErrorResponseDto buildErrorResponse(int status, String error, String message) {
        return ErrorResponseDto.builder()
                .status(status)
                .error(error)
                .message(message)
                .build();
    }

    private String resolveReasonPhrase(HttpStatusCode statusCode) {
        HttpStatus status = HttpStatus.resolve(statusCode.value());
        return status != null ? status.getReasonPhrase() : statusCode.toString();
    }
}
