package dev.rikthipranadhik.bhagkori.repository;

import dev.rikthipranadhik.bhagkori.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShareRepository extends JpaRepository<Share,Long> {

    List<Share> findByExpenseId(Long expenseId);
    List<Share> findByCreditor_IdAndExpense_Room_Id(Long creditor_Id, Long expense_RoomId);
    List<Share> findByDebtor_IdAndExpense_Room_Id(Long debtor_Id, Long expense_RoomId);
    List<Share> findByDebtorIdAndCreditorIdAndExpense_Room_Id(Long debtor_id, Long creditor_id, Long expense_room_id);
}
