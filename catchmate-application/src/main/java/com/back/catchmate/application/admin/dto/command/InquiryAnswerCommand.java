package com.back.catchmate.application.admin.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InquiryAnswerCommand {
    private Long inquiryId;
    private String content;
}
