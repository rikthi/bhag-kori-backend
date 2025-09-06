package dev.rikthipranadhik.bhagkori.service.utility.split;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy {
    @Override
    public Map<Long, BigDecimal> split(BigDecimal amount, Map<Long, BigDecimal> userShares) {

        Map<Long, BigDecimal> result = new HashMap<Long, BigDecimal>();

        for (Map.Entry<Long, BigDecimal> entry : userShares.entrySet()) {
            BigDecimal percentage = entry.getValue();
            BigDecimal calculatedAmount = amount.multiply(percentage).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            result.put(entry.getKey(), calculatedAmount);
        }

        return result;
    }
}
