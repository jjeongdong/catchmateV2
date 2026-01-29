package com.back.catchmate.application.board.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GameCreateCommand {
    private final Long homeClubId;
    private final Long awayClubId;
    private final LocalDateTime gameStartDate;
    private final String location;
}
