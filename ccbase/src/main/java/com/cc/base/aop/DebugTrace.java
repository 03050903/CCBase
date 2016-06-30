/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.cc.base.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义了一个注解，这个注解可以记录方法执行的参数，执行时间。同时可以在方法执行的时候添加自己另外的逻辑。
 * 现在添加的逻辑主要是记录方法执行的参数和时间，以此来追踪用户的行为和测试方法执行的性能
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE})
public @interface DebugTrace
{}
