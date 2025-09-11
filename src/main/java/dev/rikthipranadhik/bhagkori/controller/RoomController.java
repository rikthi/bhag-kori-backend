package dev.rikthipranadhik.bhagkori.controller;

import dev.rikthipranadhik.bhagkori.domain.dto.RoomDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.mapper.RoomMapper;
import dev.rikthipranadhik.bhagkori.domain.requests.MemberAddToRoomRequest;
import dev.rikthipranadhik.bhagkori.domain.responses.UserAndShare;
import dev.rikthipranadhik.bhagkori.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path="api/v1/room")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @PostMapping("/create")
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto){
        Long creatorId = roomDto.creatorId();
        Room room = roomMapper.fromDto(roomDto);

        return ResponseEntity.ok(roomMapper.toDto(roomService.createRoom(creatorId, room)));
    }

    @PostMapping("/add/member")
    public ResponseEntity<RoomDto> addMember(@RequestBody MemberAddToRoomRequest memberAddToRoomRequest){
        return ResponseEntity.ok(roomMapper.toDto(roomService.addMember(memberAddToRoomRequest.roomId(), memberAddToRoomRequest.memberId())));
    }

    @DeleteMapping("remove/member")
    public ResponseEntity<RoomDto> removeMember(@RequestBody MemberAddToRoomRequest memberAddToRoomRequest){
        return ResponseEntity.ok(roomMapper.toDto(roomService.removeMember(memberAddToRoomRequest.roomId(), memberAddToRoomRequest.memberId())));
    }

    @GetMapping("/get/{roomId}/userShares/{userId}")
    public ResponseEntity<List<UserAndShare>> getUserTotalShares(@PathVariable Long roomId, @PathVariable Long userId){

    }


}
