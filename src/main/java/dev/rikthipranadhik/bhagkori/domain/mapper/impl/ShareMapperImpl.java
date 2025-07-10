package dev.rikthipranadhik.bhagkori.domain.mapper.impl;

import dev.rikthipranadhik.bhagkori.domain.dto.ShareDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Expense;
import dev.rikthipranadhik.bhagkori.domain.entity.Share;
import dev.rikthipranadhik.bhagkori.domain.entity.User;
import dev.rikthipranadhik.bhagkori.domain.mapper.ShareMapper;
import org.springframework.stereotype.Component;

@Component
public class ShareMapperImpl implements ShareMapper {
    @Override
    public ShareDto toDto(Share share) {
        return new ShareDto(
                share.getId(),
                share.getExpense().getId(),
                share.getDebtor().getId(),
                share.getCreditor().getId(),
                share.getAmount()
        );
    }

    @Override
    public Share fromDto(ShareDto shareDto) {
        return new Share(
                shareDto.id(),
                new Expense(),
                new User(),
                new User(),
                shareDto.amount()
        );
    }
}
