package com.back.catchmate.infrastructure.persistence.enroll.repository;

import com.back.catchmate.infrastructure.persistence.enroll.entity.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaEnrollRepository extends JpaRepository<EnrollEntity, Long> {
    Optional<EnrollEntity> findByUserIdAndBoardId(Long userId, Long boardId);
}
