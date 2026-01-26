package com.back.catchmate.infrastructure.persistence.user.repository;

import com.back.catchmate.domain.user.model.User;
import com.back.catchmate.domain.user.repository.UserRepository;
import com.back.catchmate.infrastructure.persistence.user.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

import static com.back.catchmate.infrastructure.persistence.club.entity.QClubEntity.clubEntity;
import static com.back.catchmate.infrastructure.persistence.user.entity.QUserEntity.userEntity;
import static com.querydsl.core.group.GroupBy.groupBy;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public User save(User user) {
        UserEntity entity = UserEntity.from(user);
        UserEntity saved = jpaUserRepository.save(entity);
        return saved.toModel();
    }

    @Override
    public Optional<User> findByProviderId(String providerId) {
        return jpaUserRepository.findByProviderId(providerId)
                .map(UserEntity::toModel);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(UserEntity::toModel);
    }

    @Override
    public boolean existsByNickName(String nickName) {
        return jpaUserRepository.existsByNickName(nickName);
    }

    @Override
    public long count() {
        return jpaUserRepository.count();
    }

    @Override
    public long countByGender(Character gender) {
        return jpaUserRepository.countByGender(gender);
    }

    @Override
    public Map<String, Long> countUsersByClub() {
        return jpaQueryFactory
                .from(userEntity)
                .join(userEntity.club, clubEntity)
                .transform(groupBy(clubEntity.name).as(userEntity.count()));
    }

    @Override
    public Map<String, Long> countUsersByWatchStyle() {
        return jpaQueryFactory
                .from(userEntity)
                .where(userEntity.watchStyle.isNotNull())
                .transform(groupBy(userEntity.watchStyle).as(userEntity.count()));
    }
}
