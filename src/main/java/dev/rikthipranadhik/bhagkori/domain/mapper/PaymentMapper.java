package dev.rikthipranadhik.bhagkori.domain.mapper;

import dev.rikthipranadhik.bhagkori.domain.dto.PaymentDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Payment;

public interface PaymentMapper {
    PaymentDto toDto(Payment payment);
    Payment fromDto(PaymentDto paymentDto);
}
