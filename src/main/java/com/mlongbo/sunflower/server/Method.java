package com.mlongbo.sunflower.server;

import java.lang.annotation.*;

/**
 * Mapping is used to configure Router.
 * @author malongbo
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Method {
    MethodType[] value();
}