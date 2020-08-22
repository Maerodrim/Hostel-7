package com.ssau.Hostel7.service.servceImpl;

import com.ssau.Hostel7.dto.response.RoomResponseDto;
import com.ssau.Hostel7.helper.DtoUtils;
import com.ssau.Hostel7.model.Room;
import com.ssau.Hostel7.model.enumModel.RoomType;
import com.ssau.Hostel7.repository.RoomRepository;
import com.ssau.Hostel7.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final DtoUtils dtoUtils;

    @Override
    @Transactional(readOnly = true)
    public RoomResponseDto getRandomSpareRoom(RoomType roomType) {
        List<UUID> unoccupiedRooms = roomRepository.findAllUnoccupiedRoomsByRoomType(roomType);

        UUID randomRoomId = randomIdFromList(unoccupiedRooms);
        if (randomRoomId == null) {
            return null;
        }
        Optional<Room> roomOpt = roomRepository.findById(randomRoomId);
        Room room = roomOpt.orElseThrow(() -> new RuntimeException("Room deleted!"));

        return dtoUtils.getRoomResponseDto(room);
    }

    private UUID randomIdFromList(List<UUID> uuids) {
        if (uuids.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int index = random.nextInt(uuids.size());
        return uuids.get(index);
    }

}
