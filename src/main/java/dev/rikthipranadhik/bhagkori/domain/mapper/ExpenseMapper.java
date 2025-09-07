package dev.rikthipranadhik.bhagkori.domain.mapper;

import dev.rikthipranadhik.bhagkori.domain.dto.ExpenseDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Expense;
import dev.rikthipranadhik.bhagkori.domain.requests.ExpenseCreateRequest;

public interface ExpenseMapper {
    ExpenseDto toDto (Expense expense);
    Expense fromDto (ExpenseDto expenseDto);
    Expense fromRequest(ExpenseCreateRequest expenseCreateRequest);
}
