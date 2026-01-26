package com.back.catchmate.api.notice.controller;

import com.back.catchmate.api.notice.dto.request.NoticeCreateRequest;
import com.back.catchmate.api.notice.dto.request.NoticeUpdateRequest;
import com.back.catchmate.application.common.PagedResponse;
import com.back.catchmate.application.notice.NoticeUseCase;
import com.back.catchmate.application.notice.dto.response.NoticeActionResponse;
import com.back.catchmate.application.notice.dto.response.NoticeCreateResponse;
import com.back.catchmate.application.notice.dto.response.NoticeDetailResponse;
import com.back.catchmate.application.notice.dto.response.NoticeResponse;
import com.back.catchmate.global.annotation.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[공통] 공지사항 API")
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeUseCase noticeUseCase;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "공지사항 등록", description = "공지사항을 등록할 수 있습니다. (관리자 전용)")
    public ResponseEntity<NoticeCreateResponse> createNotice(@AuthUser Long userId,
                                                             @RequestBody @Valid NoticeCreateRequest request) {
        return ResponseEntity.ok(noticeUseCase.createNotice(userId, request.toCommand()));
    }

    @GetMapping
    @Operation(summary = "공지사항 목록 조회", description = "공지사항 목록을 페이징하여 조회합니다. (page는 0부터 시작)")
    public ResponseEntity<PagedResponse<NoticeResponse>> getNotices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(noticeUseCase.getNoticeList(page, size));
    }

    @GetMapping("/{noticeId}")
    @Operation(summary = "공지사항 상세 조회", description = "특정 공지사항의 상세 내용을 조회합니다.")
    public ResponseEntity<NoticeDetailResponse> getNoticeDetail(@PathVariable Long noticeId) {
        return ResponseEntity.ok(noticeUseCase.getNoticeDetail(noticeId));
    }

    @PutMapping("/{noticeId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "공지사항 수정", description = "특정 공지사항을 수정합니다. (관리자 전용)")
    public ResponseEntity<NoticeDetailResponse> updateNotice(@AuthUser Long userId,
                                                             @PathVariable Long noticeId,
                                                             @RequestBody @Valid NoticeUpdateRequest request) {
        return ResponseEntity.ok(noticeUseCase.updateNotice(noticeId, request.toCommand()));
    }

    @DeleteMapping("/{noticeId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "공지사항 삭제", description = "특정 공지사항을 삭제합니다. (관리자 전용)")
    public ResponseEntity<NoticeActionResponse> deleteNotice(@AuthUser Long userId,
                                                             @PathVariable Long noticeId) {
        return ResponseEntity.ok(noticeUseCase.deleteNotice(noticeId));
    }
}
