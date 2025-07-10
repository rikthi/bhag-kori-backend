package dev.rikthipranadhik.bhagkori.service;

import dev.rikthipranadhik.bhagkori.domain.entity.User;

public interface UserService {
    User createAccount(User user);
    User login(User user);
    void deleteAccount(User user);
    // User blockUser(User blocker, User blocked);
    User findByEmail(String email);
    User findByPhoneNumber(Integer phoneNumber);
}
