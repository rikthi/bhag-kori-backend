package dev.rikthipranadhik.bhagkori.controller;

import dev.rikthipranadhik.bhagkori.domain.dto.UserDto;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.mapper.UserMapper;
import dev.rikthipranadhik.bhagkori.domain.requests.LoginRequest;
import dev.rikthipranadhik.bhagkori.service.RoomService;
import dev.rikthipranadhik.bhagkori.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path="api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest loginRequest) throws IllegalArgumentException{
        UserDto response;
        try {
            response = userMapper.toDto(userService.login(loginRequest));
        } catch (IllegalArgumentException illegalArgumentException){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }


    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userMapper.toDto(userService.createAccount(userMapper.fromDto(userDto))));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteAccount(userId);
        return ResponseEntity.ok("User Deleted");
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userMapper.toDto(userService.updateAccount(userMapper.fromDto(userDto))));
    }
}
