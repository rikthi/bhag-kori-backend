package dev.rikthipranadhik.bhagkori.service;

import dev.rikthipranadhik.bhagkori.domain.entity.User;

import java.util.List;

public interface UserService {
    User createAccount(User user);
    User login(User user);
    void deleteAccount(User user);
    // User blockUser(User blocker, User blocked);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    List<User> getAllUsers();
    User updateAccount(Long id, User newUser);
}
