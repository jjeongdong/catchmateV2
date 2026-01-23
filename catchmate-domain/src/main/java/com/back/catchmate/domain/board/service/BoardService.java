package com.back.catchmate.domain.board.service;

import com.back.catchmate.domain.board.dto.BoardSearchCondition;
import com.back.catchmate.domain.board.model.Board;
import com.back.catchmate.domain.board.repository.BoardRepository;
import com.back.catchmate.domain.common.DomainPage;
import com.back.catchmate.domain.common.DomainPageable;
import error.ErrorCode;
import error.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new BaseException(ErrorCode.BOARD_NOT_FOUND));
    }

    public DomainPage<Board> getBoardList(BoardSearchCondition condition, DomainPageable pageable) {
        return boardRepository.findAllByCondition(condition, pageable);
    }

    public Optional<Board> findUncompletedBoard(Long userId) {
        return boardRepository.findFirstByUserIdAndIsCompletedFalse(userId);
    }

    public void updateBoard(Board board) {
        boardRepository.save(board);
    }

    public void deleteBoard(Board board) {
        boardRepository.delete(board);
    }
}
