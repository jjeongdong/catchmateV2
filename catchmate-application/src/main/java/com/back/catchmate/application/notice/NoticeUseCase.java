package com.back.catchmate.application.notice;

import com.back.catchmate.application.common.PagedResponse;
import com.back.catchmate.application.notice.dto.command.NoticeCreateCommand;
import com.back.catchmate.application.notice.dto.response.NoticeCreateResponse;
import com.back.catchmate.application.notice.dto.response.NoticeDetailResponse;
import com.back.catchmate.application.notice.dto.response.NoticeResponse;
import com.back.catchmate.domain.common.DomainPage;
import com.back.catchmate.domain.common.DomainPageable;
import com.back.catchmate.domain.notice.model.Notice;
import com.back.catchmate.domain.notice.service.NoticeService;
import com.back.catchmate.domain.user.model.User;
import com.back.catchmate.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeUseCase {
    private final NoticeService noticeService;
    private final UserService userService;

    @Transactional
    public NoticeCreateResponse createNotice(Long userId, NoticeCreateCommand command) {
        // 작성자(관리자) 정보 조회
        User writer = userService.getUserById(userId);

        Notice notice = Notice.createNotice(
                writer,
                command.getTitle(),
                command.getContent()
        );

        Notice savedNotice = noticeService.createNotice(notice);
        return NoticeCreateResponse.from(savedNotice.getId());
    }

    // 공지사항 상세 조회
    public NoticeDetailResponse getNoticeDetail(Long noticeId) {
        Notice notice = noticeService.getNotice(noticeId);
        return NoticeDetailResponse.from(notice);
    }

    // 공지사항 목록 조회 (페이징)
    public PagedResponse<NoticeResponse> getNoticeList(int page, int size) {
        DomainPageable domainPageable = new DomainPageable(page, size);

        DomainPage<Notice> noticePage = noticeService.getAllNotices(domainPageable);

        List<NoticeResponse> responses = noticePage.getContent().stream()
                .map(NoticeResponse::from)
                .collect(Collectors.toList());

        return new PagedResponse<>(noticePage, responses);
    }
}
