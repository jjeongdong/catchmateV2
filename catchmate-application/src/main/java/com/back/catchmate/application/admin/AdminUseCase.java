package com.back.catchmate.application.admin;

import com.back.catchmate.application.admin.dto.response.AdminDashboardResponse;
import com.back.catchmate.domain.board.service.BoardService;
import com.back.catchmate.domain.inquiry.service.InquiryService;
import com.back.catchmate.domain.report.service.ReportService;
import com.back.catchmate.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminUseCase {
    private final UserService userService;
    private final BoardService boardService;
    private final ReportService reportService;
    private final InquiryService inquiryService;

    public AdminDashboardResponse getDashboardStats() {
        return AdminDashboardResponse.builder()
                .totalUserCount(userService.getTotalUserCount())
                .genderRatio(new AdminDashboardResponse.GenderRatio(
                        userService.getUserCountByGender('M'),
                        userService.getUserCountByGender('F')
                ))
                .totalBoardCount(boardService.getTotalBoardCount())
                .userCountByClub(userService.getUserCountByClub())
                .userCountByWatchStyle(userService.getUserCountByWatchStyle())
                .totalReportCount(reportService.getTotalReportCount())
                .totalInquiryCount(inquiryService.getTotalInquiryCount())
                .build();
    }
}
