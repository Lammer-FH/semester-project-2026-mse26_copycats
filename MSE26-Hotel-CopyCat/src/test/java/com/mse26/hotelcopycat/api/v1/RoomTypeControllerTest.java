package com.mse26.hotelcopycat.api.v1;

import com.mse26.hotelcopycat.api.dtos.v1.RoomExtraResponseDto;
import com.mse26.hotelcopycat.api.dtos.v1.RoomTypeAvailabilityResponseDto;
import com.mse26.hotelcopycat.api.dtos.v1.RoomTypeResponseDto;
import com.mse26.hotelcopycat.service.RoomTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import java.util.List;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.mockito.ArgumentCaptor;

@WebMvcTest(RoomTypeController.class)
class RoomTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RoomTypeService roomTypeService;

    @Test
    void getAllRoomTypesReturnsPagedJson() throws Exception {
        RoomExtraResponseDto extra = RoomExtraResponseDto.builder()
                .name("WiFi")
                .iconPath("/images/extras/wifi.svg")
                .build();
        RoomTypeResponseDto roomType = RoomTypeResponseDto.builder()
                .id(1)
                .name("Deluxe Suite")
                .description("A spacious suite with a view of the city.")
                .imagePath("/images/rooms/1.jpg")
                .extras(List.of(extra))
                .build();

        PageRequest pageable = PageRequest.of(0, 5);
        given(roomTypeService.findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(roomType), pageable, 1));

        mockMvc.perform(get("/api/v1/room-types")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Deluxe Suite"))
                .andExpect(jsonPath("$.content[0].extras[0].name").value("WiFi"));

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(roomTypeService).findAll(pageableCaptor.capture());

        Pageable capturedPageable = pageableCaptor.getValue();
        assertThat(capturedPageable.getPageNumber()).isEqualTo(0);
        assertThat(capturedPageable.getPageSize()).isEqualTo(5);
        assertThat(capturedPageable.getSort().getOrderFor("id")).isNotNull();
        assertThat(capturedPageable.getSort().getOrderFor("id").isAscending()).isTrue();
    }

    @Test
    void getAvailabilityReturnsAvailabilityJson() throws Exception {
        RoomTypeAvailabilityResponseDto availability = RoomTypeAvailabilityResponseDto.builder()
                .roomTypeId(1)
                .checkIn(LocalDate.of(2026, 7, 1))
                .checkOut(LocalDate.of(2026, 7, 5))
                .available(true)
                .build();

        given(roomTypeService.getAvailability(1, LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 5)))
                .willReturn(availability);

        mockMvc.perform(get("/api/v1/room-types/1/availability")
                        .param("checkIn", "2026-07-01")
                        .param("checkOut", "2026-07-05")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomTypeId").value(1))
                .andExpect(jsonPath("$.checkIn").value("2026-07-01"))
                .andExpect(jsonPath("$.checkOut").value("2026-07-05"))
                .andExpect(jsonPath("$.available").value(true));
    }
}
