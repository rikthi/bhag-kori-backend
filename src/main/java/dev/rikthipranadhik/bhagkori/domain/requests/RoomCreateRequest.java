package dev.rikthipranadhik.bhagkori.domain.requests;

import dev.rikthipranadhik.bhagkori.domain.dto.RoomDto;

import java.util.Set;

public record RoomCreateRequest(RoomDto roomDto, Set<String> emails) {
}
