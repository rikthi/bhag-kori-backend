package dev.rikthipranadhik.bhagkori.domain.mapper.impl;

import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.mapper.SplitsMapper;
import dev.rikthipranadhik.bhagkori.domain.responses.Splits;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;

@Component
public class SplitsMapperImpl implements SplitsMapper {
    @Override
    public Splits toSplits(User user, BigDecimal amount) {
        return new Splits(user.getName(), amount);
    }
}
