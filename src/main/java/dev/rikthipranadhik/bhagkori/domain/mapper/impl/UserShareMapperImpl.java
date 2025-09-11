package dev.rikthipranadhik.bhagkori.domain.mapper.impl;

import dev.rikthipranadhik.bhagkori.domain.mapper.UserShareMapper;
import dev.rikthipranadhik.bhagkori.domain.responses.UserAndShare;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UserShareMapperImpl implements UserShareMapper {
    @Override
    public List<UserAndShare> toUserAndShare(HashMap<String, BigDecimal> userTotals) {
        List<UserAndShare> userAndShares = new ArrayList<>();
        for (HashMap.Entry<String, BigDecimal> userTotal: userTotals.entrySet()){
            userAndShares.add(new UserAndShare(userTotal.getKey(), userTotal.getValue()));
        }
        return userAndShares;
    }
}
