package dev.rikthipranadhik.bhagkori.service;

import dev.rikthipranadhik.bhagkori.domain.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(Long roomId, Long payerId, Expense expense);
    Expense updateExpense(Expense expense);
    void deleteExpense(Long expenseId);
    List<Expense> getAllExpenses(Long roomId);
    List<Expense> getExpensesByPayer(Long payerId);

}
