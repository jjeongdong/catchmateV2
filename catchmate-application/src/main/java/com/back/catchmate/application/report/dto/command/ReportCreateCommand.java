package com.back.catchmate.application.report.dto.command;

import com.back.catchmate.domain.report.model.Report;
import com.back.catchmate.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import report.ReportReason;

@Getter
@Builder
public class ReportCreateCommand {
    private Long reportedUserId;
    private ReportReason reason;
    private String description;

    public Report toEntity(User reporter, User reportedUser) {
        return Report.builder()
                .reporter(reporter)
                .reportedUser(reportedUser)
                .reason(this.reason)
                .description(this.description)
                .build();
    }
}
