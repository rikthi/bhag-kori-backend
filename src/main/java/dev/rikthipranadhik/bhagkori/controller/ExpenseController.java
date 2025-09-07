package dev.rikthipranadhik.bhagkori.controller;

import dev.rikthipranadhik.bhagkori.domain.dto.ExpenseDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Expense;
import dev.rikthipranadhik.bhagkori.domain.mapper.ExpenseMapper;
import dev.rikthipranadhik.bhagkori.domain.requests.ExpenseCreateRequest;
import dev.rikthipranadhik.bhagkori.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping(path="api/v1/expense")
@CrossOrigin("*")
@AllArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper;

    @PostMapping("/create")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseCreateRequest expenseCreateRequest) {
        Long roomId = expenseCreateRequest.roomId();
        Long payerId = expenseCreateRequest.payerId();
        Map<Long, BigDecimal> userSplits = expenseCreateRequest.userSplits();

        Expense expense = expenseMapper.fromRequest(expenseCreateRequest);

        return ResponseEntity.ok(expenseMapper.toDto(expenseService.createExpense(roomId, payerId, expense, userSplits)));
    }
}
