package com.mlongbo.sunflower.server;

import org.omg.PortableInterceptor.Interceptor;

import java.lang.annotation.*;

/**
 * Before is used to configure Interceptor or Validator.
 * @author malongbo
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Before {
    Class<? extends Interceptor>[] value();
}