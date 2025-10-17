package dev.rikthipranadhik.bhagkori.controller;

import dev.rikthipranadhik.bhagkori.domain.dto.RoomDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.mapper.RoomMapper;
import dev.rikthipranadhik.bhagkori.domain.mapper.UserShareMapper;
import dev.rikthipranadhik.bhagkori.domain.requests.MemberAddToRoomRequest;
import dev.rikthipranadhik.bhagkori.domain.requests.RoomCreateRequest;
import dev.rikthipranadhik.bhagkori.domain.responses.UserAndShare;
import dev.rikthipranadhik.bhagkori.service.RoomService;
import dev.rikthipranadhik.bhagkori.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping(path="api/v1/room")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;
    private final UserShareMapper userShareMapper;


    @PostMapping("/create/new")
    public ResponseEntity<RoomDto> createNewRoom(@RequestBody RoomCreateRequest roomCreateRequest){
        RoomDto roomDto = roomCreateRequest.roomDto();
        Long creatorId = roomDto.creatorId();
        Set<String> emails = roomCreateRequest.emails();
        Room room = roomMapper.fromDto(roomDto);

        return ResponseEntity.ok(roomMapper.toDto(roomService.createNewRoom(creatorId, room, emails)));
    }
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

    @GetMapping("/get/{roomId}/shares/{userId}")
    public ResponseEntity<List<UserAndShare>> getUserTotalShares(@PathVariable Long roomId, @PathVariable Long userId){
        return ResponseEntity.ok(userShareMapper.toUserAndShare(roomService.getUserTotals(roomId, userId)));
    }

    @GetMapping("/get/{roomId}/total/user/{userId}")
    public ResponseEntity<BigDecimal> getUserBalance(@PathVariable Long roomId, @PathVariable Long userId ){
        return ResponseEntity.ok(roomService.getUserBalance(roomId, userId));
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<RoomDto>> getUserRooms(@PathVariable Long userId){
        return ResponseEntity.ok(roomService.getByUser(userId).stream().map(roomMapper::toDto).toList());
    }


}
