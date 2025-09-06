package dev.rikthipranadhik.bhagkori.service.utility.split;

import java.math.BigDecimal;
import java.util.Map;

public class ExactSplitStrategy implements SplitStrategy{

    @Override
    public Map<Long, BigDecimal> split(BigDecimal amount, Map<Long, BigDecimal> userShares) {
        return userShares;
    }
}
