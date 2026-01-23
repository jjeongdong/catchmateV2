package com.back.catchmate.domain.board.repository;

import com.back.catchmate.domain.board.dto.BoardSearchCondition;
import com.back.catchmate.domain.board.model.Board;
import com.back.catchmate.domain.common.DomainPage;
import com.back.catchmate.domain.common.DomainPageable;

import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    Optional<Board> findById(Long id);
    Optional<Board> findFirstByUserIdAndIsCompletedFalse(Long userId);
    DomainPage<Board> findAllByCondition(BoardSearchCondition condition, DomainPageable pageable);
    DomainPage<Board> findAllByUserId(Long userId, DomainPageable pageable);
    void delete(Board board);
}
