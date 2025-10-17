package dev.rikthipranadhik.bhagkori.domain.responses;

import dev.rikthipranadhik.bhagkori.domain.dto.UserDto;

import java.math.BigDecimal;

public record UserAndShare(UserDto user, BigDecimal amount) {
}
