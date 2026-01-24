package com.back.catchmate.infrastructure.persistence.enroll.repository;

import com.back.catchmate.domain.board.model.Board;
import com.back.catchmate.domain.enroll.model.Enroll;
import com.back.catchmate.domain.enroll.repository.EnrollRepository;
import com.back.catchmate.domain.user.model.User;
import com.back.catchmate.infrastructure.persistence.enroll.entity.EnrollEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EnrollRepositoryImpl implements EnrollRepository {
    private final JpaEnrollRepository jpaEnrollRepository;

    @Override
    public Enroll save(Enroll enroll) {
        EnrollEntity entity = EnrollEntity.from(enroll);
        return jpaEnrollRepository.save(entity).toModel();
    }

    @Override
    public Optional<Enroll> findById(Long id) {
        return jpaEnrollRepository.findById(id)
                .map(EnrollEntity::toModel);
    }

    @Override
    public Optional<Enroll> findByUserAndBoard(User user, Board board) {
        return jpaEnrollRepository.findByUserIdAndBoardId(user.getId(), board.getId())
                .map(EnrollEntity::toModel);
    }

    @Override
    public void delete(Enroll enroll) {
        jpaEnrollRepository.deleteById(enroll.getId());
    }
}
