package dev.rikthipranadhik.bhagkori.domain.mapper.impl;

import dev.rikthipranadhik.bhagkori.domain.dto.PaymentDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Payment;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.mapper.PaymentMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapperImpl implements PaymentMapper {
    @Override
    public PaymentDto toDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getPayer().getId(),
                payment.getPayee().getId(),
                payment.getAmount()
        );
    }

    @Override
    public Payment fromDto(PaymentDto paymentDto) {
        return new Payment(
                paymentDto.id(),
                new User(),
                new User(),
                paymentDto.amount()
        );
    }
}
