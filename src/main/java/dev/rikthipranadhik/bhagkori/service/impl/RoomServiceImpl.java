package dev.rikthipranadhik.bhagkori.service.impl;

import dev.rikthipranadhik.bhagkori.domain.entity.Payment;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.Share;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.repository.PaymentRepository;
import dev.rikthipranadhik.bhagkori.repository.RoomRepository;
import dev.rikthipranadhik.bhagkori.repository.ShareRepository;
import dev.rikthipranadhik.bhagkori.repository.UserRepository;
import dev.rikthipranadhik.bhagkori.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {


    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ShareRepository shareRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

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

        room.setCreator(creator);
        Set<User> users = new HashSet<>();
        users.add(creator);
        room.setMembers(users);
        Room finalRoom = roomRepository.save(room);
        userRepository.save(creator);
        return finalRoom;
    }

    @Override
    public Room createNewRoom(Long creatorId, Room room, Set<String> emails) {
        createRoom(creatorId, room);
        for (String email : emails) {
            User user = userRepository.findByEmail(email);
            if (user == null){
                System.out.println("User not found with email: " + email);
                continue;
            }
            addMember(room.getId(), user.getId());
        }
        return roomRepository.findById(room.getId()).orElse(null);
    }

    @Override
    public Set<Room> getByUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            throw new IllegalArgumentException("User not found");
        }

        return user.getRooms();
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

    @Override
    public HashMap<User, BigDecimal> getUserTotals(Long roomId, Long memberId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        User member = userRepository.findById(memberId).orElse(null);

        if (room == null || member == null){
            throw new IllegalArgumentException("Room or member not found");
        }

        HashMap<User, BigDecimal> userTotals= new HashMap<>();
        for (User u : room.getMembers()) {
            if (u.getId().equals(memberId)) {
                continue;
            }

            BigDecimal total = getIndividualTotal(roomId, memberId, u);


            userTotals.put(u, total);
        }

        return userTotals;
    }



    private BigDecimal getIndividualTotal(Long roomId, Long memberId, User u) {
        List<Share> sharesCreditedToMember = shareRepository.findByDebtorIdAndCreditorIdAndExpense_Room_Id(memberId, u.getId(), roomId);
        BigDecimal totalCreditedAmount = sharesCreditedToMember.stream()
                .map(Share :: getAmount)
                .filter(Objects:: nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Share> sharesInDebtFromMember = shareRepository.findByDebtorIdAndCreditorIdAndExpense_Room_Id(u.getId(), memberId, roomId);
        BigDecimal totalInDebtAmount = sharesInDebtFromMember.stream()
                .map(Share :: getAmount)
                .filter(Objects:: nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Payment> paymentsGivenToMember = paymentRepository.findByPayerIdAndPayeeIdAndRoomId(u.getId(), memberId, roomId);
        BigDecimal totalPaidAmount = paymentsGivenToMember.stream()
                .map(Payment :: getAmount)
                .filter(Objects :: nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Payment> paymentsGivenFromMember = paymentRepository.findByPayerIdAndPayeeIdAndRoomId(memberId, u.getId(), roomId);
        BigDecimal totalReceivedAmount = paymentsGivenFromMember.stream()
                .map(Payment :: getAmount)
                .filter(Objects :: nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal memberCredit = totalInDebtAmount.add(totalReceivedAmount);
        BigDecimal memberDebt = totalCreditedAmount.add(totalPaidAmount);
        return memberCredit.subtract(memberDebt);
    }

    @Override
    public BigDecimal getUserBalance(Long roomId, Long userId) {
        HashMap<User, BigDecimal> totals = new HashMap<>();

        totals = getUserTotals(roomId, userId);

        if (totals.isEmpty()){
            return BigDecimal.ZERO;
        }

        BigDecimal balance = BigDecimal.ZERO;

        for (BigDecimal total : totals.values()) {
            balance = balance.add(total);
        }

        return balance;
    }

    @Override
    public Set<User> getMembersByRoomId(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null){
            throw new IllegalArgumentException("Room not found");
        }

        return room.getMembers();
    }

    @Override
    public Room addMemberByEmail(Long roomId, String email) {
        Room repoRoom = roomRepository.findById(roomId).orElse(null);
        if (repoRoom == null){
            throw new IllegalArgumentException("Room not found");
        }


        User member = userRepository.findByEmail(email);

        if (member == null){
            throw new IllegalArgumentException("Member not found");
        }

        if (repoRoom.getMembers().contains(member)){
            throw new IllegalArgumentException("Member already exists");
        }

        Set<User> members = repoRoom.getMembers();
        members.add(member);
        repoRoom.setMembers(members);


        Set<Room> rooms = member.getRooms();
        rooms.add(repoRoom);
        member.setRooms(rooms);
        userRepository.save(member);

        return roomRepository.save(repoRoom);
    }
}
