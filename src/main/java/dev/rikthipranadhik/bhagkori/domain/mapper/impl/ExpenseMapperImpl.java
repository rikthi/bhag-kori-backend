package dev.rikthipranadhik.bhagkori.domain.mapper.impl;

import dev.rikthipranadhik.bhagkori.domain.dto.ExpenseDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Expense;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.mapper.ExpenseMapper;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapperImpl implements ExpenseMapper {

    @Override
    public Expense fromDto(ExpenseDto expenseDto) {
        return new Expense(
                expenseDto.id(),
                expenseDto.name(),
                expenseDto.createTime(),
                new User(),
                new Room(),
                expenseDto.amount()
        );
    }

    @Override
    public ExpenseDto toDto(Expense expense) {
        return new ExpenseDto(
                expense.getId(),
                expense.getName(),
                expense.getCreateTime(),
                expense.getPayer().getId(),
                expense.getRoom().getId(),
                expense.getAmount()
        );
    }
}
