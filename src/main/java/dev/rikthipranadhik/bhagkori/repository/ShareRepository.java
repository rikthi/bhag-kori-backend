package dev.rikthipranadhik.bhagkori.repository;

import dev.rikthipranadhik.bhagkori.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share,Long> {
}
