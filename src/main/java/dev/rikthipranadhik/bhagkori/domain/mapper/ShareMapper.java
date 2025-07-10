package dev.rikthipranadhik.bhagkori.domain.mapper;

import dev.rikthipranadhik.bhagkori.domain.dto.ShareDto;
import dev.rikthipranadhik.bhagkori.domain.entity.Share;

public interface ShareMapper {

    ShareDto toDto(Share share);
    Share fromDto(ShareDto shareDto);
}
