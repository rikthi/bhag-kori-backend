package dev.rikthipranadhik.bhagkori.domain.responses;

import dev.rikthipranadhik.bhagkori.domain.dto.ExpenseDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public record ExpenseAndSplits(ExpenseDto expenseDto, Map<Long, BigDecimal> userSplits) {
}
