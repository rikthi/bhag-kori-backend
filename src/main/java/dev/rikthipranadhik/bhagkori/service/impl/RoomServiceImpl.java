package dev.rikthipranadhik.bhagkori.service.impl;

import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.repository.RoomRepository;
import dev.rikthipranadhik.bhagkori.repository.UserRepository;
import dev.rikthipranadhik.bhagkori.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {


    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public Room createRoom(Long creatorId, Room room) {
        if (room.getId() != null){
            throw new IllegalStateException("Room id must be null to create room");
        }

        User creator = userRepository.findById(creatorId).orElse(null);
        if (creator == null){
            throw new IllegalArgumentException("Creation of room failed: no user found with id: " + creatorId);
        }

        Set<Room> rooms = creator.getRooms();
        rooms.add(room);
        creator.setRooms(rooms);
        userRepository.save(creator);

        room.setCreator(creator);
        Set<User> users = new HashSet<>();
        users.add(creator);
        room.setMembers(users);
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
    public Room addMember(Long roomId, Long memberId) {
        Room repoRoom = roomRepository.findById(roomId).orElse(null);
        User member = userRepository.findById(memberId).orElse(null);

        if (repoRoom == null){
            throw new RuntimeException("Room not found");
        }

        if (member == null){
            throw new RuntimeException("Member not found");
        }

        for (User u : repoRoom.getMembers()) {
            if (u.getId().equals(memberId)) {
                throw new RuntimeException("Member already exists");
            }
        }

        Set<User> members;
        members = repoRoom.getMembers();
        members.add(member);
        repoRoom.setMembers(members);


        Set<Room> rooms = member.getRooms();
        rooms.add(repoRoom);
        member.setRooms(rooms);
        userRepository.save(member);

        return roomRepository.save(repoRoom);

    }

    @Override
    public Room removeMember(Long roomId, Long memberId) {
        Room repoRoom = roomRepository.findById(roomId).orElse(null);
        User member = userRepository.findById(memberId).orElse(null);

        if (repoRoom == null){
            throw new RuntimeException("Room not found");
        }

        if (member == null){
            throw new RuntimeException("Member not found");
        }

        User toBeRemoved = null;
        for (User u : repoRoom.getMembers()) {
            if (u.getId().equals(memberId)) {
                toBeRemoved = u;
            }
        }

        if (toBeRemoved == null){
            throw new IllegalArgumentException("Member not found");
        }

        Set<Room> rooms = member.getRooms();
        rooms.remove(repoRoom);
        member.setRooms(rooms);
        userRepository.save(member);

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
