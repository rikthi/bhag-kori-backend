package dev.rikthipranadhik.bhagkori.repository;

import dev.rikthipranadhik.bhagkori.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByRoomId(Long id);
    List<Payment> findByPayerId(Long id);
    List<Payment> findByPayeeId(Long id);
}
