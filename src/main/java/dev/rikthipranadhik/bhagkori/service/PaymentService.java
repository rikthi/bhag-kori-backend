package dev.rikthipranadhik.bhagkori.service;

import dev.rikthipranadhik.bhagkori.domain.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(Long roomId, Long payerId, Long payeeId, Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getPaymentsByRoomId(Long roomId);
    List<Payment> getPaymentsByPayerId(Long payerId);
    List<Payment> getPaymentsByPayeeId(Long payeeId);
    Payment updatePayment(Payment payment);
    void deletePayment(Long id);

}