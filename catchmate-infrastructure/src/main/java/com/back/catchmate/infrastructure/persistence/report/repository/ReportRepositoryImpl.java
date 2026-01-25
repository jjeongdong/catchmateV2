package com.back.catchmate.infrastructure.persistence.report.repository;

import com.back.catchmate.domain.report.model.Report;
import com.back.catchmate.domain.report.repository.ReportRepository;
import com.back.catchmate.infrastructure.persistence.report.entity.ReportEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {
    private final JpaReportRepository jpaReportRepository;

    @Override
    public Report save(Report report) {
        ReportEntity entity = ReportEntity.from(report);
        return jpaReportRepository.save(entity).toModel();
    }
}
