package dev.rikthipranadhik.bhagkori.service;

import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.User;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoomService {
    Room getRoomById(Long roomId);
    Room createRoom(Long creatorId, Room room);
    Room updateRoom(Room room);
    void deleteRoom(Room room);
    Room addMember(Long roomId, Long memberId);
    Room removeMember(Long roomId, Long memberId);
    Set<User> getMembers(Room room);
    HashMap<User, BigDecimal> getUserTotals(Long roomId, Long memberId);
    Room createNewRoom(Long creatorId, Room room, Set<String> emails);
    Set<Room> getByUser(Long userId);
    BigDecimal getUserBalance(Long roomId, Long userId);
}
