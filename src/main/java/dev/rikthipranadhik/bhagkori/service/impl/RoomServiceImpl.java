package dev.rikthipranadhik.bhagkori.service.impl;

import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.repository.RoomRepository;
import dev.rikthipranadhik.bhagkori.repository.UserRepository;
import dev.rikthipranadhik.bhagkori.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {


    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Room room) {
        return null;
    }

    @Override
    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public Room addMember(Room room, User member) {
        Room repoRoom = roomRepository.findById(room.getId()).orElse(null);

        if (repoRoom == null){
            throw new RuntimeException("Room not found");
        }

        for (User u : repoRoom.getMembers()) {
            if (u.getId().equals(member.getId())) {
                throw new RuntimeException("Member already exists");
            }
        }

        Set<User> members = repoRoom.getMembers();
        members.add(member);
        repoRoom.setMembers(members);

        return roomRepository.save(repoRoom);

    }

    @Override
    public Room removeMember(Room room, User member) {
        Room repoRoom = roomRepository.findById(room.getId()).orElse(null);

        if (repoRoom == null){
            throw new RuntimeException("Room not found");
        }

        User toBeRemoved = null;
        for (User u : repoRoom.getMembers()) {
            if (u.getId().equals(member.getId())) {
                toBeRemoved = u;
            }
        }
        if (toBeRemoved == null){
            throw new IllegalArgumentException("Member not found");
        }

        Set<User> members = repoRoom.getMembers();
        members.remove(member);
        repoRoom.setMembers(members);

        return roomRepository.save(repoRoom);
    }

    @Override
    public Set<User> getMembers(Room room) {
        Room repoRoom = roomRepository.findById(room.getId()).orElse(null);
        if (repoRoom == null){
            throw new IllegalArgumentException("Room not found");
        }

        return repoRoom.getMembers();
    }
}
