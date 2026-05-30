package com.mse26.hotelcopycat.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void countDistinctRoomIdCountsOnlyOverlappingBookingsOfRequestedRoomType() {
        jdbcTemplate.update(
                "insert into room_types (id, name, description, image_path) values (?, ?, ?, ?)",
                1, "Deluxe Suite", "A spacious suite with a view of the city.", "/images/rooms/1.jpg"
        );
        jdbcTemplate.update(
                "insert into room_types (id, name, description, image_path) values (?, ?, ?, ?)",
                2, "Business Room", "A quiet room tailored for work trips and short stays.", "/images/rooms/2.jpg"
        );
        jdbcTemplate.update(
                "insert into rooms (id, room_nr, id_room_types) values (?, ?, ?)",
                1, "101", 1
        );
        jdbcTemplate.update(
                "insert into rooms (id, room_nr, id_room_types) values (?, ?, ?)",
                2, "102", 1
        );
        jdbcTemplate.update(
                "insert into rooms (id, room_nr, id_room_types) values (?, ?, ?)",
                3, "301", 2
        );
        jdbcTemplate.update(
                "insert into bookings (id, room_id, check_in, check_out) values (?, ?, ?, ?)",
                1, 1, LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 5)
        );
        jdbcTemplate.update(
                "insert into bookings (id, room_id, check_in, check_out) values (?, ?, ?, ?)",
                2, 2, LocalDate.of(2026, 7, 10), LocalDate.of(2026, 7, 12)
        );
        jdbcTemplate.update(
                "insert into bookings (id, room_id, check_in, check_out) values (?, ?, ?, ?)",
                3, 3, LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 5)
        );

        long overlappingBookings = bookingRepository
                .countBookedRoomsForRoomTypeInPeriod(
                        1,
                        LocalDate.of(2026, 7, 4),
                        LocalDate.of(2026, 7, 2)
                );

        assertThat(overlappingBookings).isEqualTo(1);
    }
}
