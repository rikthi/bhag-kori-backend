package dev.rikthipranadhik.bhagkori.service;

import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.User;

public interface RoomService {
    Room createRoom(Room room);
    Room updateRoom(Room room);
    void deleteRoom(Room room);
    User addMember(User member);
    User removeMember(User member);
}
