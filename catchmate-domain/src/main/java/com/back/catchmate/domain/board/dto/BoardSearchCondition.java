package com.back.catchmate.domain.board.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class BoardSearchCondition {
    private final Long userId;
    private final LocalDate gameDate;
    private final Integer maxPerson;
    private final List<Long> preferredTeamIdList;
    private final List<Long> blockedUserIds;
}
