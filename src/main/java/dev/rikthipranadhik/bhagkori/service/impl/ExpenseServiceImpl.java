package dev.rikthipranadhik.bhagkori.service.impl;

import dev.rikthipranadhik.bhagkori.domain.entity.Expense;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.Share;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.repository.ExpenseRepository;
import dev.rikthipranadhik.bhagkori.repository.RoomRepository;
import dev.rikthipranadhik.bhagkori.repository.ShareRepository;
import dev.rikthipranadhik.bhagkori.repository.UserRepository;
import dev.rikthipranadhik.bhagkori.service.ExpenseService;
import dev.rikthipranadhik.bhagkori.service.utility.split.SplitStrategy;
import dev.rikthipranadhik.bhagkori.service.utility.split.SplitStrategyFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    private ShareRepository shareRepository;


    @Override
    public Expense createExpense(Long roomId, Long payerId, Expense expense, Map<Long, BigDecimal> userSplits) {
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

        expenseRepository.save(expense);


        SplitStrategy strategy = SplitStrategyFactory.getStrategy(expense.getSplitType());

        Map<Long, BigDecimal> id_splits = strategy.split(expense.getAmount(), userSplits);

        for (Map.Entry<Long, BigDecimal> entry : id_splits.entrySet()) {
            User debtor = userRepository.findById(entry.getKey()).orElse(null);
            if (debtor == null) {
                throw new IllegalArgumentException("Debtor couldn't be found");
            }
            Share share = new Share(null, expense, debtor, payer, entry.getValue());
            shareRepository.save(share);
        }

        return expense;
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
