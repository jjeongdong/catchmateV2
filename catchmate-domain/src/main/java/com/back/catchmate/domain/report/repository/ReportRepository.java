package com.back.catchmate.domain.report.repository;

import com.back.catchmate.domain.report.model.Report;

public interface ReportRepository {
    Report save(Report report);
}
