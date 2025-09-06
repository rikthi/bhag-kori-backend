package dev.rikthipranadhik.bhagkori.service.impl;

import dev.rikthipranadhik.bhagkori.domain.entity.Expense;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.repository.ExpenseRepository;
import dev.rikthipranadhik.bhagkori.repository.RoomRepository;
import dev.rikthipranadhik.bhagkori.repository.UserRepository;
import dev.rikthipranadhik.bhagkori.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;


    @Override
    public Expense createExpense(Long roomId, Long payerId, Expense expense) {
        Room room = roomRepository.findById(roomId).orElse(null);
        User payer = userRepository.findById(payerId).orElse(null);

        if (room == null || payer == null) {
            throw new IllegalArgumentException("Expense couldn't be created! Room or payer not found");
        }

        if (expense.getId() != null){
            throw new IllegalArgumentException("Expense Id must be null to create a new Expense");
        }

        expense.setRoom(room);
        expense.setPayer(payer);
        return expenseRepository.save(expense);

    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public List<Expense> getAllExpenses(Long roomId) {
        return expenseRepository.findByRoomId(roomId);
    }

    @Override
    public List<Expense> getExpensesByPayer(Long payerId) {
        return expenseRepository.findByPayerId(payerId);
    }
}
