package dev.rikthipranadhik.bhagkori.domain.dto;

import java.math.BigDecimal;

public record PaymentDto (Long id, Long payerId, Long payeeId, BigDecimal amount){
}
