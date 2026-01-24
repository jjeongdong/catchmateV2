package com.back.catchmate.infrastructure.persistence.enroll.repository;

import com.back.catchmate.domain.enroll.model.AcceptStatus;
import com.back.catchmate.infrastructure.persistence.enroll.entity.EnrollEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaEnrollRepository extends JpaRepository<EnrollEntity, Long> {
    Optional<EnrollEntity> findByUserIdAndBoardId(Long userId, Long boardId);

    @Query("SELECT e FROM EnrollEntity e " +
            "JOIN FETCH e.board b " +
            "JOIN FETCH b.user " +
            "JOIN FETCH b.cheerClub " +
            "JOIN FETCH b.game " +
            "WHERE e.user.id = :userId")
    Page<EnrollEntity> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT e FROM EnrollEntity e " +
            "JOIN FETCH e.user " +
            "WHERE e.board.id = :boardId " +
            "AND e.acceptStatus = :acceptStatus")
    Page<EnrollEntity> findAllByBoardId(@Param("boardId") Long boardId,
                                        @Param("acceptStatus") AcceptStatus acceptStatus,
                                        Pageable pageable);
}
