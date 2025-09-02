package dev.rikthipranadhik.bhagkori.controller;

import dev.rikthipranadhik.bhagkori.domain.dto.RoomDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.mapper.RoomMapper;
import dev.rikthipranadhik.bhagkori.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(path="api/v1/room")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(@RequestBody RoomDto roomDto){
        Long creatorId = roomDto.creatorId();
        Room room = roomMapper.fromDto(roomDto);

        return ResponseEntity.ok(roomService.createRoom(creatorId, room));
    }

}
