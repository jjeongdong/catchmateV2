package com.back.catchmate.domain.notice.service;

import com.back.catchmate.domain.common.DomainPage;
import com.back.catchmate.domain.common.DomainPageable;
import com.back.catchmate.domain.notice.model.Notice;
import com.back.catchmate.domain.notice.repository.NoticeRepository;
import error.ErrorCode;
import error.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    // 공지사항 상세 조회
    public Notice getNotice(Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTICE_NOT_FOUND));
    }

    // 공지사항 목록 조회 (페이징)
    public DomainPage<Notice> getAllNotices(DomainPageable pageable) {
        return noticeRepository.findAll(pageable);
    }
}
