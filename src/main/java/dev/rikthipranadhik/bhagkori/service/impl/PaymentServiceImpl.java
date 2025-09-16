package dev.rikthipranadhik.bhagkori.service.impl;

import dev.rikthipranadhik.bhagkori.domain.entity.Payment;
import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.repository.PaymentRepository;
import dev.rikthipranadhik.bhagkori.repository.RoomRepository;
import dev.rikthipranadhik.bhagkori.repository.UserRepository;
import dev.rikthipranadhik.bhagkori.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public Payment createPayment(Long roomId, Long payerId, Long payeeId, Payment payment) {
        if (payment.getId() != null){
            throw new IllegalArgumentException("Payment id must be null");
        }

        Room room = roomRepository.findById(roomId).orElseThrow(()-> new IllegalArgumentException("Room not found"));
        User payer = userRepository.findById(payerId).orElseThrow(()-> new IllegalArgumentException("Payer not found"));
        User payee = userRepository.findById(payeeId).orElseThrow(()-> new IllegalArgumentException("Payee not found"));

        payment.setRoom(room);

        payment.setPayer(payer);
        payment.setPayee(payee);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Payment not found"));
    }

    @Override
    public List<Payment> getPaymentsByRoomId(Long roomId) {
        return paymentRepository.findByRoomId(roomId);
    }

    @Override
    public List<Payment> getPaymentsByPayerId(Long payerId) {
        return paymentRepository.findByPayerId(payerId);
    }

    @Override
    public List<Payment> getPaymentsByPayeeId(Long payeeId) {
        return paymentRepository.findByPayeeId(payeeId);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return null;
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
