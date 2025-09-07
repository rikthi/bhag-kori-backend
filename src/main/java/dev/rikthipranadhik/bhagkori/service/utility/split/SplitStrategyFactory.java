package dev.rikthipranadhik.bhagkori.service.utility.split;

import dev.rikthipranadhik.bhagkori.domain.enums.SplitStrategyType;

public class SplitStrategyFactory {
    public static SplitStrategy getStrategy(SplitStrategyType splitStrategyType){
        return switch (splitStrategyType){
            case EQUAL -> new EqualSplitStrategy();
            case PERCENTAGE -> new PercentageSplitStrategy();
            case EXACT -> new ExactSplitStrategy();
        };
    }
}
