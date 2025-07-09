package dev.rikthipranadhik.bhagkori.domain.dto;

import java.time.LocalDateTime;

public record ExpenseDto (Long id, String name, LocalDateTime createTime, Long payerId, Long roomId) {
}
