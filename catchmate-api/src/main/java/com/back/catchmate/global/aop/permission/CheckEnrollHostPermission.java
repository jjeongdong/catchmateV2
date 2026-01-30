package com.back.catchmate.global.aop.permission;

import com.back.catchmate.application.enroll.service.EnrollHostFinder;
import com.back.catchmate.domain.common.permission.CheckDataPermission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// Finder 클래스를 직접 명시!
@CheckDataPermission(finder = EnrollHostFinder.class)
public @interface CheckEnrollHostPermission {
}
