package com.back.catchmate.domain.common.permission;

public interface DomainFinder<T extends ResourceOwnership> {
    T searchById(Long id); // ID로 도메인 객체 조회
}
