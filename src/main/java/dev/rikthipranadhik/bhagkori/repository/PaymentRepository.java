package dev.rikthipranadhik.bhagkori.repository;

import dev.rikthipranadhik.bhagkori.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
