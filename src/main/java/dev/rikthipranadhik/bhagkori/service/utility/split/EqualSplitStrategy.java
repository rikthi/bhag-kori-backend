package dev.rikthipranadhik.bhagkori.service.utility.split;

import dev.rikthipranadhik.bhagkori.domain.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public Map<Long, BigDecimal> split(BigDecimal amount, Map<Long, BigDecimal> userShares) {
        Integer size = userShares.size();

        BigDecimal individualShare = amount.divide(BigDecimal.valueOf(size), 2, RoundingMode.HALF_UP);

        Map<Long, BigDecimal> result = new HashMap<Long, BigDecimal>();

        for (Long user: userShares.keySet()) {
            result.put(user, individualShare);
        }

        return result;
    }
}
