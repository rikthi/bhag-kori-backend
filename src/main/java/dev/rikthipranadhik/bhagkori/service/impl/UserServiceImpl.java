package dev.rikthipranadhik.bhagkori.service.impl;

import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.requests.LoginRequest;
import dev.rikthipranadhik.bhagkori.repository.RoomRepository;
import dev.rikthipranadhik.bhagkori.repository.UserRepository;
import dev.rikthipranadhik.bhagkori.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public User createAccount(User user) {
        if (user.getId() != null) {
            throw new IllegalStateException("User id must be null to create account");
        }

        return userRepository.save(user);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        if (loginRequest.email() == null || loginRequest.password() == null) {
            throw new IllegalArgumentException("Email and password must be provided");
        }

        User user = userRepository.findByEmail(loginRequest.email());
        if (user == null || !user.getPassword().equals(loginRequest.password())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return user;
    }

    @Override
    public void deleteAccount(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateAccount(User newUser) {
        Long id = newUser.getId();
        User oldUser = userRepository.findById(id).orElse(null);

        if (oldUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPhoneNumber(newUser.getPhoneNumber());
        oldUser.setPassword(newUser.getPassword());
        return userRepository.save(oldUser);
    }
}
