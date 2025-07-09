package dev.rikthipranadhik.bhagkori.domain.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record RoomDto (Long id, String name, String description, LocalDateTime createTime, Long creatorId, Set<Long> memberIds){
}
