package com.back.catchmate.domain.report.service;

import com.back.catchmate.domain.report.model.Report;
import com.back.catchmate.domain.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public long getTotalReportCount() {
        return reportRepository.count();
    }
}
