package com.back.catchmate.api.admin.controller;

import com.back.catchmate.application.admin.AdminUseCase;
import com.back.catchmate.application.admin.dto.response.AdminDashboardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[관리자] 관리자 관련 PI")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminUseCase adminUseCase;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard/stats")
    @Operation(summary = "관리자 대시보드 통계 조회")
    public ResponseEntity<AdminDashboardResponse> getDashboardStats() {
        return ResponseEntity.ok(adminUseCase.getDashboardStats());
    }
}
