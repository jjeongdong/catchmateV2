package com.back.catchmate.api.enroll.controller;

import com.back.catchmate.api.enroll.dto.request.EnrollCreateRequest;
import com.back.catchmate.application.common.PagedResponse;
import com.back.catchmate.application.enroll.EnrollUseCase;
import com.back.catchmate.application.enroll.dto.response.EnrollDetailResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollReceiveResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollCancelResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollCreateResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollApplicantResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollRequestResponse;
import com.back.catchmate.global.annotation.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[사용자] 직관 신청 관련 API")
@RestController
@RequiredArgsConstructor
public class EnrollController {
    private final EnrollUseCase enrollUseCase;

    @PostMapping("/api/boards/{boardId}/enrolls")
    @Operation(summary = "직관 신청 등록", description = "게시글에 대해 직관 신청을 합니다.")
    public ResponseEntity<EnrollCreateResponse> createEnroll(@AuthUser Long userId,
                                                             @PathVariable Long boardId,
                                                             @Valid @RequestBody EnrollCreateRequest request) {
        return ResponseEntity.ok(enrollUseCase.createEnroll(request.toCommand(userId, boardId)));
    }

    @GetMapping("/api/enrolls/request")
    @Operation(summary = "내가 보낸 직관 신청 목록 조회", description = "내가 신청한 직관 신청 목록을 조회합니다.")
    public ResponseEntity<PagedResponse<EnrollRequestResponse>> getRequestEnrollList(
            @AuthUser Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(enrollUseCase.getRequestEnrollList(userId, page, size));
    }

    @GetMapping("/api/enrolls/receive")
    @Operation(summary = "내가 받은 직관 신청 목록 조회 (단일 게시글 상세)", description = "특정 게시글(boardId)에 들어온 신청자 목록을 페이징하여 조회합니다.")
    public ResponseEntity<PagedResponse<EnrollApplicantResponse>> getReceiveEnrollListByBordId(
            @AuthUser Long userId,
            @RequestParam Long boardId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(enrollUseCase.getReceiveEnrollListByBordId(userId, boardId, page, size));
    }

    @GetMapping("/api/enrolls/receive/all")
    @Operation(summary = "내가 받은 직관 신청 목록 조회 (전체 게시글)", description = "게시글 단위로 페이징하며, 각 게시글에는 신청자 목록이 포함됩니다.")
    public ResponseEntity<PagedResponse<EnrollReceiveResponse>> getAllReceiveEnrollList(
            @AuthUser Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(enrollUseCase.getAllReceiveEnrollList(userId, page, size));
    }

    @GetMapping("/api/enrolls/{enrollId}")
    @Operation(summary = "직관 신청 단일 상세 조회", description = "특정 신청 내역(enrollId)의 상세 정보를 조회합니다. (신청자 본인 또는 게시글 작성자만 가능)")
    public ResponseEntity<EnrollDetailResponse> getEnrollDetail(
            @AuthUser Long userId,
            @PathVariable Long enrollId
    ) {
        return ResponseEntity.ok(enrollUseCase.getEnrollDetail(userId, enrollId));
    }

    @DeleteMapping("/api/enrolls/{enrollId}")
    @Operation(summary = "직관 신청 취소 API", description = "직관 신청을 취소하는 API 입니다.")
    public ResponseEntity<EnrollCancelResponse> cancelEnroll(@PathVariable Long enrollId,
                                                             @AuthUser Long userId) {
        return ResponseEntity.ok(enrollUseCase.cancelEnroll(enrollId, userId));
    }
}
