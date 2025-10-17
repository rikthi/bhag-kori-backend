package dev.rikthipranadhik.bhagkori.domain.responses;

import java.math.BigDecimal;
import java.util.HashMap;

public record Splits(String username, BigDecimal amount) {
}
