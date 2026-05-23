package com.mse26.hotelcopycat.Service;
import com.mse26.hotelcopycat.Model.RoomType;
import com.mse26.hotelcopycat.Repository.RoomTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomDataService {
    private final RoomTypeRepository roomTypeRepository;

    public RoomDataService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public Page<RoomType> findAll(Pageable pageable){
        return roomTypeRepository.findAllBy(pageable);
    }

    public Optional<RoomType> findById(int id){
        return roomTypeRepository.findWithRoomAndRoomExtrasById(id);
    }
}
