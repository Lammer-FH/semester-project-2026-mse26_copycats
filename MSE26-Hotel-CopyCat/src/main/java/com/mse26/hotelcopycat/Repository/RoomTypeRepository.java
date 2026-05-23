package com.mse26.hotelcopycat.Repository;

import com.mse26.hotelcopycat.Model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    @EntityGraph(attributePaths = {"room", "roomExtras"})
    Page<RoomType> findAllBy(Pageable pageable);

    @EntityGraph(attributePaths = {"room", "roomExtras"})
    Optional<RoomType> findWithRoomAndRoomExtrasById(Integer id);
}
