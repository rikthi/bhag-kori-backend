package dev.rikthipranadhik.bhagkori.domain.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public record ExpenseCreateRequest(Long id, String name, LocalDateTime createTime, Long payerId, Long roomId, BigDecimal amount, String splitType, Map<Long, BigDecimal> userSplits) {
}
