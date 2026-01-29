package com.back.catchmate.application.enroll.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EnrollCreateCommand {
    private Long userId;
    private Long boardId;
    private String description;
}
