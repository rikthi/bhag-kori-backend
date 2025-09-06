package dev.rikthipranadhik.bhagkori.service.utility.split;

import dev.rikthipranadhik.bhagkori.domain.entity.Share;
import dev.rikthipranadhik.bhagkori.domain.entity.User;

import java.math.BigDecimal;
import java.util.Map;

public interface SplitStrategy {
    Map<Long, BigDecimal> split(BigDecimal amount, Map<Long, BigDecimal> userShares);
}
