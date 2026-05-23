package com.mse26.hotelcopycat.repository;

import com.mse26.hotelcopycat.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    @EntityGraph(attributePaths = {"rooms", "roomExtras"})
    Page<RoomType> findAllBy(Pageable pageable);

    @EntityGraph(attributePaths = {"rooms", "roomExtras"})
    Optional<RoomType> findWithRoomAndRoomExtrasById(Integer id);
}
