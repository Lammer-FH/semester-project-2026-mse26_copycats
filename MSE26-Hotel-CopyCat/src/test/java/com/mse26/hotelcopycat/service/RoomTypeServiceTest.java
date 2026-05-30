package com.mse26.hotelcopycat.service;

import com.mse26.hotelcopycat.api.dtos.v1.RoomTypeAvailabilityResponseDto;
import com.mse26.hotelcopycat.mapper.RoomTypeMapper;
import com.mse26.hotelcopycat.model.RoomType;
import com.mse26.hotelcopycat.repository.BookingRepository;
import com.mse26.hotelcopycat.repository.RoomRepository;
import com.mse26.hotelcopycat.repository.RoomTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class RoomTypeServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomTypeRepository roomTypeRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomTypeMapper roomTypeMapper;

    @Test
    void getAvailabilityReturnsTrueWhenThereIsAtLeastOneFreeRoom() {
        RoomTypeService service = new RoomTypeService(roomRepository, roomTypeRepository, bookingRepository, roomTypeMapper);

        given(roomTypeRepository.findById(1)).willReturn(Optional.of(new RoomType()));
        given(roomRepository.countByRoomType_Id(1)).willReturn(2L);
        given(bookingRepository.countBookedRoomsForRoomTypeInPeriod(
                1,
                LocalDate.of(2026, 7, 5),
                LocalDate.of(2026, 7, 1)
        )).willReturn(1L);

        RoomTypeAvailabilityResponseDto result = service.getAvailability(
                1,
                LocalDate.of(2026, 7, 1),
                LocalDate.of(2026, 7, 5)
        );

        assertThat(result.isAvailable()).isTrue();
    }

    @Test
    void getAvailabilityRejectsInvalidDateRange() {
        RoomTypeService service = new RoomTypeService(roomRepository, roomTypeRepository, bookingRepository, roomTypeMapper);

        assertThatThrownBy(() -> service.getAvailability(
                1,
                LocalDate.of(2026, 7, 5),
                LocalDate.of(2026, 7, 5)
        ))
                .isInstanceOf(ResponseStatusException.class)
                .extracting(error -> ((ResponseStatusException) error).getStatusCode())
                .isEqualTo(BAD_REQUEST);
    }

    @Test
    void getAvailabilityFailsWhenRoomTypeDoesNotExist() {
        RoomTypeService service = new RoomTypeService(roomRepository, roomTypeRepository, bookingRepository, roomTypeMapper);

        given(roomTypeRepository.findById(99)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.getAvailability(
                99,
                LocalDate.of(2026, 7, 1),
                LocalDate.of(2026, 7, 5)
        ))
                .isInstanceOf(ResponseStatusException.class)
                .extracting(error -> ((ResponseStatusException) error).getStatusCode())
                .isEqualTo(NOT_FOUND);
    }
}
