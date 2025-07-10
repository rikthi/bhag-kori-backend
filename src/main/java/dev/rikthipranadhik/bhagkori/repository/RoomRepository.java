package dev.rikthipranadhik.bhagkori.repository;

import dev.rikthipranadhik.bhagkori.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
