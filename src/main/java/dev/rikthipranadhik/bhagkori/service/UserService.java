package dev.rikthipranadhik.bhagkori.service;

import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.requests.LoginRequest;

import java.util.List;

public interface UserService {
    User createAccount(User user);
    User login(LoginRequest loginRequest);
    void deleteAccount(Long userId);
    // User blockUser(User blocker, User blocked);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    List<User> getAllUsers();
    User updateAccount(User newUser);
}
