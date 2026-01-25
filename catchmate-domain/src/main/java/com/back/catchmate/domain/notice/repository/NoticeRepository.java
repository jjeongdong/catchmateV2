package com.back.catchmate.domain.notice.repository;

import com.back.catchmate.domain.common.DomainPage;
import com.back.catchmate.domain.common.DomainPageable;
import com.back.catchmate.domain.notice.model.Notice;

import java.util.Optional;

public interface NoticeRepository {
    Optional<Notice> findById(Long noticeId);
    DomainPage<Notice> findAll(DomainPageable pageable);
}
