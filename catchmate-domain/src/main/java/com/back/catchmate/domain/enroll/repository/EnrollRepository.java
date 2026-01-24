package com.back.catchmate.domain.enroll.repository;

import com.back.catchmate.domain.board.model.Board;
import com.back.catchmate.domain.common.DomainPage;
import com.back.catchmate.domain.common.DomainPageable;
import com.back.catchmate.domain.enroll.model.Enroll;
import com.back.catchmate.domain.user.model.User;

import java.util.Optional;

public interface EnrollRepository {
    Enroll save(Enroll enroll);
    Optional<Enroll> findById(Long id);
    Optional<Enroll> findByUserAndBoard(User user, Board board);
    DomainPage<Enroll> findAllByUserId(Long userId, DomainPageable pageable);
    void delete(Enroll enroll);
}
