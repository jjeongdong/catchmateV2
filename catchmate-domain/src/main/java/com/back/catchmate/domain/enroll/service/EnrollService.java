package com.back.catchmate.domain.enroll.service;

import com.back.catchmate.domain.board.model.Board;
import com.back.catchmate.domain.enroll.model.AcceptStatus;
import com.back.catchmate.domain.enroll.model.Enroll;
import com.back.catchmate.domain.enroll.repository.EnrollRepository;
import com.back.catchmate.domain.user.model.User;
import error.ErrorCode;
import error.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollService {
    private final EnrollRepository enrollRepository;

    public Enroll requestEnrollment(User user, Board board, String description) {
        validateDuplicateEnroll(user, board);

        Enroll enroll = Enroll.createEnroll(user, board, description);
        return enrollRepository.save(enroll);
    }

    private void validateDuplicateEnroll(User user, Board board) {
        enrollRepository.findByUserAndBoard(user, board)
                .ifPresent(existingEnroll -> {
                    if (existingEnroll.getAcceptStatus() == AcceptStatus.PENDING) {
                        throw new BaseException(ErrorCode.ALREADY_ENROLL_PENDING);
                    }
                    if (existingEnroll.getAcceptStatus() == AcceptStatus.REJECTED) {
                        throw new BaseException(ErrorCode.ALREADY_ENROLL_REJECTED);
                    }
                    if (existingEnroll.getAcceptStatus() == AcceptStatus.ACCEPTED) {
                        throw new BaseException(ErrorCode.ALREADY_ENROLL_ACCEPTED);
                    }
                });
    }

    public Enroll getEnrollById(Long enrollId) {
        return enrollRepository.findById(enrollId)
                .orElseThrow(() -> new BaseException(ErrorCode.ENROLL_NOT_FOUND));
    }

    public void cancelEnrollment(Enroll enroll, User user) {
        // 권한 확인
        if (!enroll.getUser().getId().equals(user.getId())) {
            throw new BaseException(ErrorCode.FORBIDDEN_ACCESS);
        }

        enrollRepository.delete(enroll);
    }
}
