package com.back.catchmate.application.user.service;

import com.back.catchmate.domain.common.permission.DomainFinder;
import com.back.catchmate.domain.user.model.User;
import com.back.catchmate.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPermissionFinder implements DomainFinder<User> {
    private final UserService userService;

    @Override
    public User searchById(Long userId) {
        return userService.getUserById(userId);
    }
}
