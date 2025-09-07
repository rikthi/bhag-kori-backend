package dev.rikthipranadhik.bhagkori.service;

import dev.rikthipranadhik.bhagkori.domain.entity.Expense;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ExpenseService {
    Expense createExpense(Long roomId, Long payerId, Expense expense, Map<Long, BigDecimal> userSplits);
    Expense updateExpense(Expense expense);
    void deleteExpense(Long expenseId);
    List<Expense> getAllExpenses(Long roomId);
    List<Expense> getExpensesByPayer(Long payerId);

}
