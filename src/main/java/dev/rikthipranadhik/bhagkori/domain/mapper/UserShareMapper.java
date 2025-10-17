package dev.rikthipranadhik.bhagkori.domain.mapper;

import dev.rikthipranadhik.bhagkori.domain.entity.Share;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.responses.UserAndShare;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface UserShareMapper {
    List<UserAndShare> toUserAndShare(HashMap<User, BigDecimal> userTotals);
}
