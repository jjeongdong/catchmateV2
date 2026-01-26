package com.back.catchmate.application.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class AdminDashboardResponse {
    private long totalUserCount;
    private GenderRatio genderRatio;
    private long totalBoardCount;
    private Map<String, Long> userCountByClub; // 구단명 : 인원수
    private Map<String, Long> userCountByWatchStyle; // 응원스타일 : 인원수
    private long totalReportCount;
    private long totalInquiryCount;

    @Getter
    @AllArgsConstructor
    public static class GenderRatio {
        private long maleCount;
        private long femaleCount;
    }
}
