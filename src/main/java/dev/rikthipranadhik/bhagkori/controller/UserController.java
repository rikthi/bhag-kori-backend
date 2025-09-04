package dev.rikthipranadhik.bhagkori.controller;

import dev.rikthipranadhik.bhagkori.domain.dto.UserDto;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.mapper.UserMapper;
import dev.rikthipranadhik.bhagkori.service.RoomService;
import dev.rikthipranadhik.bhagkori.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(path="api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userMapper.toDto(userService.createAccount(userMapper.fromDto(userDto))));
    }

}
