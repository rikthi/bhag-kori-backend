package dev.rikthipranadhik.bhagkori.repository;

import dev.rikthipranadhik.bhagkori.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByRoomId(Long id);
    List<Payment> findByPayerId(Long id);
    List<Payment> findByPayeeId(Long id);
    List<Payment> findByPayerIdAndPayeeIdAndRoomId(Long payerId, Long payeeId, Long roomId);
}
