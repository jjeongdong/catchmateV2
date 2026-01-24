package com.back.catchmate.application.enroll;

import com.back.catchmate.application.board.dto.response.BoardResponse;
import com.back.catchmate.application.common.PagedResponse;
import com.back.catchmate.application.enroll.dto.command.EnrollCreateCommand;
import com.back.catchmate.application.enroll.dto.response.EnrollReceiveResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollCancelResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollCreateResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollApplicantResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollRequestResponse;
import com.back.catchmate.application.enroll.dto.response.EnrollResponse;
import com.back.catchmate.domain.board.model.Board;
import com.back.catchmate.domain.board.service.BoardService;
import com.back.catchmate.domain.bookmark.service.BookmarkService;
import com.back.catchmate.domain.common.DomainPage;
import com.back.catchmate.domain.common.DomainPageable;
import com.back.catchmate.domain.enroll.model.AcceptStatus;
import com.back.catchmate.domain.enroll.model.Enroll;
import com.back.catchmate.domain.enroll.service.EnrollService;
import com.back.catchmate.domain.user.model.User;
import com.back.catchmate.domain.user.service.UserService;
import error.ErrorCode;
import error.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EnrollUseCase {
    private final EnrollService enrollService;
    private final BookmarkService bookmarkService;
    private final BoardService boardService;
    private final UserService userService;

    @Transactional
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

    public PagedResponse<EnrollRequestResponse> getRequestEnrollList(Long userId, int page, int size) {
        // 도메인 페이징 객체 생성
        DomainPageable domainPageable = DomainPageable.of(page, size);
        // 직관 신청 리스트 조회
        DomainPage<Enroll> enrollPage = enrollService.getEnrollsByUserId(userId, domainPageable);

        // DTO 변환
        List<EnrollRequestResponse> responses = enrollPage.getContent().stream()
                .map(enroll -> {
                    boolean isBookMarked = bookmarkService.isBookmarked(
                            userId,
                            enroll.getBoard().getId()
                    );
                    return EnrollRequestResponse.of(enroll, isBookMarked);
                })
                .toList();

        return new PagedResponse<>(enrollPage, responses);
    }

    public PagedResponse<EnrollApplicantResponse> getReceiveEnrollListByBordId(Long userId, Long boardId, int page, int size) {
        // 도메인 페이징 객체 생성
        DomainPageable domainPageable = DomainPageable.of(page, size);
        Board board = boardService.getBoard(boardId);

        if (!board.getUser().getId().equals(userId)) {
            throw new BaseException(ErrorCode.FORBIDDEN_ACCESS);
        }

        // 직관 신청 리스트 조회
        DomainPage<Enroll> enrollPage = enrollService.getPendingEnrollsByBoardId(boardId, AcceptStatus.PENDING, domainPageable);

        // DTO 변환
        List<EnrollApplicantResponse> responses = enrollPage.getContent().stream()
                .map(EnrollApplicantResponse::from)
                .toList();

        return new PagedResponse<>(enrollPage, responses);
    }

    public PagedResponse<EnrollReceiveResponse> getAllReceiveEnrollList(Long userId, int page, int size) {
        DomainPageable pageable = DomainPageable.of(page, size);

        // 신청이 있는 게시글 ID 목록 조회
        DomainPage<Long> boardIdPage = enrollService.getBoardIdsWithPendingEnrolls(userId, pageable);
        List<Long> boardIds = boardIdPage.getContent();

        // 데이터가 없으면 빈 페이지 반환
        if (boardIds.isEmpty()) {
            return new PagedResponse<>(boardIdPage, Collections.emptyList());
        }

        // 위 게시글들에 속한 신청 전체 조회
        List<Enroll> allEnrolls = enrollService.getEnrollsByBoardIds(boardIds);

        // boardIds 순서를 지키면서 DTO 조립
        List<EnrollReceiveResponse> content = boardIds.stream()
                .map(boardId -> {
                    // 해당 게시글의 신청서만 필터링
                    List<Enroll> enrolls = allEnrolls.stream()
                            .filter(e -> e.getBoard().getId().equals(boardId))
                            .toList();

                    // 신청이 없으면 null 반환
                    if (enrolls.isEmpty()) return null;

                    // 게시글 DTO 변환
                    Board board = enrolls.get(0).getBoard();
                    BoardResponse boardResponse = BoardResponse.of(board, false);

                    // 신청자 목록 DTO 변환
                    List<EnrollResponse> enrollList = enrolls.stream()
                            .map(EnrollResponse::from)
                            .collect(Collectors.toList());

                    return EnrollReceiveResponse.of(boardResponse, enrollList);
                })
                .filter(Objects::nonNull)
                .toList();

        return new PagedResponse<>(boardIdPage, content);
    }

    @Transactional
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
