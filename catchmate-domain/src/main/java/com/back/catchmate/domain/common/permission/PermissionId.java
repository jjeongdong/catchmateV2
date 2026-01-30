package com.back.catchmate.domain.common.permission;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionId {
}
