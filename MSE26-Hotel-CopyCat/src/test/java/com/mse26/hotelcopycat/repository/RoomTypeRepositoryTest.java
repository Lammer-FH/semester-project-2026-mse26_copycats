package com.mse26.hotelcopycat.repository;

import com.mse26.hotelcopycat.model.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class RoomTypeRepositoryTest {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void findAllByReturnsPagedRoomTypesWithExtrasLoaded() {
        jdbcTemplate.update(
                "insert into room_types (id, name, description, image_path) values (?, ?, ?, ?)",
                1, "Deluxe Suite", "A spacious suite with a view of the city.", "/images/rooms/1.jpg"
        );
        jdbcTemplate.update(
                "insert into room_extras (id, name, icon_path) values (?, ?, ?)",
                1, "WiFi", "/images/extras/wifi.svg"
        );
        jdbcTemplate.update(
                "insert into room_type_room_extras (id_room_types, id_room_extras) values (?, ?)",
                1, 1
        );

        Page<RoomType> result = roomTypeRepository.findAllBy(PageRequest.of(0, 5));

        assertThat(result.getContent()).hasSize(1);
        RoomType roomType = result.getContent().get(0);
        assertThat(roomType.getName()).isEqualTo("Deluxe Suite");
        assertThat(roomType.getRoomExtras())
                .extracting(extra -> extra.getName())
                .containsExactly("WiFi");
    }
}
