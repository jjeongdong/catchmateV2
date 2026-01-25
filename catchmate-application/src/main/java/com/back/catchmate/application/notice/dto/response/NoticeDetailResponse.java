package com.back.catchmate.application.notice.dto.response;

import com.back.catchmate.domain.notice.model.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeDetailResponse {
    private Long noticeId;
    private String title;
    private String content;
    private String writerNickname;
    private LocalDateTime createdAt;

    public static NoticeDetailResponse from(Notice notice) {
        return NoticeDetailResponse.builder()
                .noticeId(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .writerNickname(notice.getWriter().getNickName())
                .createdAt(notice.getCreatedAt())
                .build();
    }
}
