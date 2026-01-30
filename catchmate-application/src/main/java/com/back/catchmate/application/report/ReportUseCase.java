package com.back.catchmate.application.report;

import com.back.catchmate.application.report.dto.command.ReportCreateCommand;
import com.back.catchmate.application.report.dto.response.ReportCreateResponse;
import com.back.catchmate.domain.report.model.Report;
import com.back.catchmate.domain.report.service.ReportService;
import com.back.catchmate.domain.user.model.User;
import com.back.catchmate.domain.user.service.UserService;
import error.ErrorCode;
import error.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReportUseCase {
    private final ReportService reportService;
    private final UserService userService;

    @Transactional
    public ReportCreateResponse createReport(Long reporterId, ReportCreateCommand command) {
        // 신고자 및 대상자 조회
        User reporter = userService.getUser(reporterId);
        User reportedUser = userService.getUser(command.getReportedUserId());

        // 유효성 검사 (본인 신고 불가)
        if (reporter.getId().equals(reportedUser.getId())) {
            throw new BaseException(ErrorCode.CANNOT_REPORT_SELF);
        }

        Report report = Report.createReport(
                reporter,
                reportedUser,
                command.getReason(),
                command.getDescription()
        );

        Report savedReport = reportService.createReport(report);
        return ReportCreateResponse.of(savedReport.getId());
    }
}
