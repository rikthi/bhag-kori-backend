package dev.rikthipranadhik.bhagkori.repository;

import dev.rikthipranadhik.bhagkori.domain.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
