package com.back.catchmate.api.enroll.controller;

import com.back.catchmate.api.enroll.dto.request.EnrollCreateRequest;
import com.back.catchmate.application.enroll.EnrollUseCase;
import com.back.catchmate.application.enroll.dto.response.EnrollCancelResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollCreateResponse;
import com.back.catchmate.global.annotation.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[사용자] 직관 신청 관련 API")
@RestController
@RequiredArgsConstructor
public class EnrollController {
    private final EnrollUseCase enrollUseCase;

    @PostMapping("/api/boards/{boardId}/enrolls")
    @Operation(summary = "직관 신청 등록", description = "게시글에 대해 직관 신청을 합니다.")
    public EnrollCreateResponse createEnroll(@AuthUser Long userId,
                                             @PathVariable Long boardId,
                                             @Valid @RequestBody EnrollCreateRequest request) {
        return enrollUseCase.createEnroll(request.toCommand(userId, boardId));
    }

    @DeleteMapping("/api/enrolls/{enrollId}")
    @Operation(summary = "직관 신청 취소 API", description = "직관 신청을 취소하는 API 입니다.")
    public EnrollCancelResponse cancelEnroll(@PathVariable Long enrollId,
                                             @AuthUser Long userId) {
        return enrollUseCase.cancelEnroll(enrollId, userId);
    }
}
