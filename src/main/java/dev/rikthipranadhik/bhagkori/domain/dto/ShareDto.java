package dev.rikthipranadhik.bhagkori.domain.dto;

import java.math.BigDecimal;

public record ShareDto(Long id, Long expenseId, Long debtorId, Long creditorId, BigDecimal amount) {
}
