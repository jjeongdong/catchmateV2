package com.back.catchmate.application.enroll.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnrollCreateCommand {
    private Long userId;
    private Long boardId;
    private String description;
}
