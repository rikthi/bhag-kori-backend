package dev.rikthipranadhik.bhagkori.service;

import dev.rikthipranadhik.bhagkori.domain.entity.Payment;

public interface PaymentService {
    Payment createPayment(Long roomId, Long payerId, Long payeeId, Payment payment);
    Payment getPaymentById(Long id);
    Payment getPaymentsByRoomId(Long roomId);
    Payment getPaymentsByPayerId(Long payerId);
    Payment getPaymentsByPayeeId(Long payeeId);
    Payment updatePayment(Payment payment);
    void deletePayment(Long id);

}