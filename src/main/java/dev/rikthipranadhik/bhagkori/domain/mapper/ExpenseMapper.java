package dev.rikthipranadhik.bhagkori.domain.mapper;

import dev.rikthipranadhik.bhagkori.domain.dto.ExpenseDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Expense;

public interface ExpenseMapper {
    ExpenseDto toDto (Expense expense);
    Expense fromDto (ExpenseDto expenseDto);
}
