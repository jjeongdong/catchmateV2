package com.back.catchmate.api.enroll.dto.request;

import com.back.catchmate.application.enroll.dto.command.EnrollCreateCommand;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnrollCreateRequest {
    private String description;

    public EnrollCreateCommand toCommand(Long userId, Long boardId) {
        return EnrollCreateCommand.builder()
                .userId(userId)
                .boardId(boardId)
                .description(description)
                .build();
    }
}
