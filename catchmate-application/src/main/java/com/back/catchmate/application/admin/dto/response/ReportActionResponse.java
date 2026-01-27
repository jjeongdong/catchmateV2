package com.back.catchmate.application.admin.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportActionResponse {
    private Long reportId;
    private Long reportedUserId;

    public static ReportActionResponse of(Long reportId, Long reportedUserId) {
        return ReportActionResponse.builder()
                .reportId(reportId)
                .reportedUserId(reportedUserId)
                .build();
    }
}
