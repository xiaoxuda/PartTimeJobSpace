package cn.orditech.annotation;

import cn.orditech.enums.AuthorizationTypeEnum;

import java.lang.annotation.*;

/**
 * Created by kimi on 2017/4/3.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorization {
    AuthorizationTypeEnum value () default AuthorizationTypeEnum.GENERAL_STAFF;
}
