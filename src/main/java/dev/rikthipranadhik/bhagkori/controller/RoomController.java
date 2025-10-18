package dev.rikthipranadhik.bhagkori.controller;

import dev.rikthipranadhik.bhagkori.domain.dto.RoomDto;
import dev.rikthipranadhik.bhagkori.domain.dto.UserDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.mapper.RoomMapper;
import dev.rikthipranadhik.bhagkori.domain.mapper.UserMapper;
import dev.rikthipranadhik.bhagkori.domain.mapper.UserShareMapper;
import dev.rikthipranadhik.bhagkori.domain.mapper.impl.UserMapperImpl;
import dev.rikthipranadhik.bhagkori.domain.requests.MemberAddToRoomRequest;
import dev.rikthipranadhik.bhagkori.domain.requests.RoomCreateRequest;
import dev.rikthipranadhik.bhagkori.domain.responses.UserAndShare;
import dev.rikthipranadhik.bhagkori.service.RoomService;
import dev.rikthipranadhik.bhagkori.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
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
    private final UserMapper userMapper;


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

    @GetMapping("get/{roomId}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable Long roomId){
        return ResponseEntity.ok(roomMapper.toDto(roomService.getRoomById(roomId)));
    }

    @PostMapping("/add/member")
    public ResponseEntity<RoomDto> addMember(@RequestBody MemberAddToRoomRequest memberAddToRoomRequest){
        return ResponseEntity.ok(roomMapper.toDto(roomService.addMember(memberAddToRoomRequest.roomId(), memberAddToRoomRequest.memberId())));
    }

    @PostMapping("/add/{roomId}/member/email/{email}")
    public ResponseEntity<RoomDto> addMemberByEmail(@PathVariable String email, @PathVariable Long roomId){
        return ResponseEntity.ok(roomMapper.toDto(roomService.addMemberByEmail(roomId, email)));
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
    public ResponseEntity<List<RoomDto>> getUserRooms(@PathVariable Long userId) {
        return ResponseEntity.ok(roomService.getByUser(userId).stream().map(roomMapper::toDto).toList());
    }

    @GetMapping("get/{roomId}/users")
    public ResponseEntity<List<UserDto>> getUsersByRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(roomService.getMembersByRoomId(roomId)
                .stream()
                .map(userMapper::toDtoSecure)
                .toList());
    }

    @DeleteMapping("delete/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long roomId){
        try {
            roomService.deleteRoomById(roomId);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Room deleted successfully");
    }

    @PutMapping("{roomId}/remove/user/{userId}")
    public ResponseEntity<String> removeUserFromRoom( @PathVariable Long roomId , @PathVariable Long userId){
        try {
            roomService.removeMember(roomId, userId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("User removed from group successfully");
    }


}
