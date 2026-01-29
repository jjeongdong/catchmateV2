package com.back.catchmate.application.report.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ReportCreateResponse {
    private Long reportId;
    private LocalDateTime createdAt;

    public static ReportCreateResponse of(Long reportId) {
        return ReportCreateResponse.builder()
                .reportId(reportId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
