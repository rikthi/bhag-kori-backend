package dev.rikthipranadhik.bhagkori.domain.mapper.impl;

import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.mapper.UserMapper;
import dev.rikthipranadhik.bhagkori.domain.mapper.UserShareMapper;
import dev.rikthipranadhik.bhagkori.domain.responses.UserAndShare;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@AllArgsConstructor
public class UserShareMapperImpl implements UserShareMapper {

    private UserMapper userMapper;

    @Override
    public List<UserAndShare> toUserAndShare(HashMap<User, BigDecimal> userTotals) {
        List<UserAndShare> userAndShares = new ArrayList<>();
        for (HashMap.Entry<User, BigDecimal> userTotal: userTotals.entrySet()){
            userAndShares.add(new UserAndShare(userMapper.toDtoSecure(userTotal.getKey()), userTotal.getValue()));
        }
        return userAndShares;
    }
}
