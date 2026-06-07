package com.mse26.hotelcopycat.repository;

import com.mse26.hotelcopycat.enums.BookingStatus;
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
                1, "Business Room", "A quiet room tailored for work trips and short stays.", "/images/rooms/2.jpg"
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
                "insert into guests (id, first_name, last_name, email) values (?, ?, ?, ?)",
                1, "Max", "Mustermann", "max@example.com"
        );

        jdbcTemplate.update(
                "insert into guests (id, first_name, last_name, email) values (?, ?, ?, ?)",
                2, "Anna", "Schmidt", "anna@example.com"
        );

        jdbcTemplate.update(
                """
                insert into bookings 
                (id, room_id, guest_id, check_in, check_out, breakfast, status, created_at)
                values (?, ?, ?, ?, ?, ?, ?, ?)
                """,
                1, 1, 1,
                LocalDate.of(2026, 7, 1),
                LocalDate.of(2026, 7, 5),
                1, BookingStatus.CONFIRMED.name(),
                "2026-06-01T10:00:00Z"
        );

        jdbcTemplate.update(
                """
                insert into bookings 
                (id, room_id, guest_id, check_in, check_out, breakfast, status, created_at)
                values (?, ?, ?, ?, ?, ?, ?, ?)
                """,
                2, 2, 2,
                LocalDate.of(2026, 7, 1),
                LocalDate.of(2026, 7, 5),
                0, BookingStatus.CONFIRMED.name(),
                "2026-06-01T10:00:00Z"
        );

        long overlappingBookings = bookingRepository
                .countBookedRoomsForRoomTypeInPeriod(
                        1,
                        LocalDate.of(2026, 7, 2),
                        LocalDate.of(2026, 7, 4)
                );

        assertThat(overlappingBookings).isEqualTo(2);
    }
}
