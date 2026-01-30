package com.back.catchmate.application.report.service;

import com.back.catchmate.domain.common.permission.DomainFinder;
import com.back.catchmate.domain.report.model.Report;
import com.back.catchmate.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportPermissionFinder implements DomainFinder<Report> {
    private final ReportService reportService;

    @Override
    public Report searchById(Long reportId) {
        return reportService.getReport(reportId);
    }
}
