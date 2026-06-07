package com.mse26.hotelcopycat.service;

import com.mse26.hotelcopycat.api.dtos.v1.BookingRequestDto;
import com.mse26.hotelcopycat.api.dtos.v1.BookingResponseDto;
import com.mse26.hotelcopycat.enums.BookingStatus;
import com.mse26.hotelcopycat.mapper.BookingMapper;
import com.mse26.hotelcopycat.model.Booking;
import com.mse26.hotelcopycat.model.Guest;
import com.mse26.hotelcopycat.model.Room;
import com.mse26.hotelcopycat.repository.BookingRepository;
import com.mse26.hotelcopycat.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@Service
public class BookingService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingService(
            RoomRepository roomRepository,
            BookingRepository bookingRepository,
            BookingMapper bookingMapper
    ) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto){
        if(!bookingRequestDto.email().equals(bookingRequestDto.confirmEmail())){
            throw new ResponseStatusException(BAD_REQUEST, "Email addresses do not match");
        }

        Guest guest = createNewGuest(bookingRequestDto);
        var chosenRoom = getAvailableRoomForBooking(bookingRequestDto);
        Booking booking = bookingMapper.toEntity(
                bookingRequestDto,
                chosenRoom,
                BookingStatus.CONFIRMED
        );
        booking.setGuest(guest);

        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toResponseDto(saved);
    }

    private Guest createNewGuest(BookingRequestDto bookingRequestDto){
        Guest guest = new Guest();
        guest.setFirstName(bookingRequestDto.firstName());
        guest.setLastName(bookingRequestDto.lastName());
        guest.setEmail(bookingRequestDto.email());

        return guest;
    }

    private Room getAvailableRoomForBooking(BookingRequestDto bookingRequestDto){
        var roomTypeId = bookingRequestDto.roomTypeId();
        var allRooms = roomRepository.findByRoomType_Id(roomTypeId);
        var bookedRooms = bookingRepository.findBookedRoomsForRoomTypeInPeriod(
                roomTypeId,
                bookingRequestDto.checkOut(),
                bookingRequestDto.checkIn()
        );
        var chosenRoom = allRooms.stream()
                .filter(room -> !bookedRooms.contains(room))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        CONFLICT,
                        "No room of type " + bookingRequestDto.roomTypeId() +
                                " is available from " + bookingRequestDto.checkIn() +
                                " to " + bookingRequestDto.checkOut()
                ));
        return chosenRoom;
    }
}
