package com.back.catchmate.application.enroll;

import com.back.catchmate.application.enroll.dto.command.EnrollCreateCommand;
import com.back.catchmate.application.enroll.dto.response.EnrollCancelResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollCreateResponse;
import com.back.catchmate.domain.board.model.Board;
import com.back.catchmate.domain.board.service.BoardService;
import com.back.catchmate.domain.enroll.model.Enroll;
import com.back.catchmate.domain.enroll.service.EnrollService;
import com.back.catchmate.domain.user.model.User;
import com.back.catchmate.domain.user.service.UserService;
import error.ErrorCode;
import error.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollUseCase {
    private final EnrollService enrollService;
    private final BoardService boardService;
    private final UserService userService;

    public EnrollCreateResponse createEnroll(EnrollCreateCommand command) {
        // 신청자 정보 조회
        User applicant = userService.getUserById(command.getUserId());

        // 게시글 정보 조회
        Board board = boardService.getCompletedBoard(command.getBoardId());

        if (applicant.getId().equals(board.getUser().getId())) {
            throw new BaseException(ErrorCode.ENROLL_BAD_REQUEST);
        }

        // 직관 신청 생성 및 저장
        Enroll savedEnroll = enrollService.requestEnrollment(applicant, board, command.getDescription());

        return EnrollCreateResponse.of(savedEnroll.getId());
    }

    public EnrollCancelResponse cancelEnroll(Long enrollId, Long userId) {
        // 신청자 정보 조회
        User user = userService.getUserById(userId);

        // 직관 신청 조회
        Enroll enroll = enrollService.getEnrollById(enrollId);

        // 직관 신청 취소
        enrollService.cancelEnrollment(enroll, user);

        return EnrollCancelResponse.of(enrollId);
    }
}
