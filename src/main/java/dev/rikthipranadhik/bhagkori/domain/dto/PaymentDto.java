package dev.rikthipranadhik.bhagkori.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDto (Long id, LocalDateTime paymentTime, Long payerId, Long payeeId, BigDecimal amount){
}
