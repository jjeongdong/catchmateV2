package com.back.catchmate.application.admin.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReportActionCommand {
    private Long reportId;

    public static ReportActionCommand of(Long reportId) {
        return ReportActionCommand.builder()
                .reportId(reportId)
                .build();
    }
}
