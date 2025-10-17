package dev.rikthipranadhik.bhagkori.controller;

import dev.rikthipranadhik.bhagkori.domain.dto.ExpenseDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Expense;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.mapper.ExpenseMapper;
import dev.rikthipranadhik.bhagkori.domain.mapper.SplitsMapper;
import dev.rikthipranadhik.bhagkori.domain.requests.ExpenseCreateRequest;
import dev.rikthipranadhik.bhagkori.domain.responses.ExpenseAndSplits;
import dev.rikthipranadhik.bhagkori.domain.responses.Splits;
import dev.rikthipranadhik.bhagkori.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/v1/expense")
@CrossOrigin("*")
@AllArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper;
    private final SplitsMapper splitsMapper;

    @PostMapping("/create")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseCreateRequest expenseCreateRequest) {
        Long roomId = expenseCreateRequest.roomId();
        Long payerId = expenseCreateRequest.payerId();
        Map<Long, BigDecimal> userSplits = expenseCreateRequest.userSplits();

        Expense expense = expenseMapper.fromRequest(expenseCreateRequest);

        return ResponseEntity.ok(expenseMapper.toDto(expenseService.createExpense(roomId, payerId, expense, userSplits)));
    }

    @GetMapping("get/{expenseId}/user/{userId}/share")
    public ResponseEntity<BigDecimal> getUserShare(@PathVariable Long expenseId, @PathVariable Long userId){
        return ResponseEntity.ok(expenseService.getExpenseShareByUserId(expenseId, userId));
    }

    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId){
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok("Expense has been deleted");
    }

    @GetMapping("/get/room/{roomId}")
    public ResponseEntity<List<ExpenseDto>> getAllExpensesByRoom(@PathVariable Long roomId){
        return ResponseEntity.ok(
                expenseService.getAllExpenses(roomId)
                        .stream()
                        .map(expenseMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("/get/{expenseId}/splits")
    public ResponseEntity<List<Splits>> getAllSplitsByExpense(@PathVariable Long expenseId){
        Map<User, BigDecimal> splits = expenseService.getSplitsByExpenseId(expenseId);
        List<Splits> nameSplits = new ArrayList<>();
        for (Map.Entry<User, BigDecimal> entry : splits.entrySet()) {
            nameSplits.add(splitsMapper.toSplits(entry.getKey(), entry.getValue()));
        }
        return ResponseEntity.ok(nameSplits);
    }


    @GetMapping("/get/{expenseId}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable Long expenseId){
        return ResponseEntity.ok(expenseMapper.toDto(expenseService.getExpenseById(expenseId)));
    }

    @GetMapping("get/payer/{payerId}")
    public ResponseEntity<List<ExpenseDto>> getAllExpensesByPayer(@PathVariable Long payerId){
        return ResponseEntity.ok(expenseService.getExpensesByPayer(payerId)
                .stream()
                .map(expenseMapper::toDto)
                .toList());
    }

}
