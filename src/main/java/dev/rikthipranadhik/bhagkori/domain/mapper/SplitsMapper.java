package dev.rikthipranadhik.bhagkori.domain.mapper;

import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.responses.Splits;

import java.math.BigDecimal;
import java.util.HashMap;

public interface SplitsMapper {
    Splits toSplits(User user, BigDecimal amount);

}
