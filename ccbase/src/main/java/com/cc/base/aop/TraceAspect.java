/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.cc.base.aop;

import android.os.Build;
import android.os.Looper;
import android.os.Trace;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

/**
 * Aspect representing the cross cutting-concern: Method and Constructor Tracing.
 */
@Aspect
public class TraceAspect
{

  /**
   * 标记添加注解的类
   */
  @Pointcut("within(@com.cc.base.aop.DebugTrace *)")
  public void withinAnnotatedClass()
  {
  }

  /**
   * 标记添加注解类中的方法
   */
  @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
  public void methodInsideAnnotatedType()
  {
  }

  /**
   * 标记添加注解类中的构造方法
   */
  @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
  public void constructorInsideAnnotatedType()
  {
  }

  /**
   * 标记方法，包括添加注解的方法和添加注解类中的方法
   */
  @Pointcut("execution(@com.cc.base.aop.DebugTrace * *(..)) || methodInsideAnnotatedType()")
  public void method()
  {
  }

  /**
   * 标记构造函数，包括添加注解的构造函数和添加注解类中的构造函数
   */
  @Pointcut("execution(@com.cc.base.aop.DebugTrace *.new(..)) || constructorInsideAnnotatedType()")
  public void constructor()
  {
  }

  /**
   * 方法的执行
   *
   * @param joinPoint 连接点
   * @return
   * @throws Throwable
   */
  @Around("method() || constructor()")
  public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable
  {
    //方法开始执行前的操作
    enterMethod(joinPoint);
    //当前的开始时间
    long startNanos = System.nanoTime();
    //获取方法执行的结果
    Object result = joinPoint.proceed();
    //方法结束的时间
    long stopNanos = System.nanoTime();
    //转换成毫秒值
    long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);
    //退出方法
    exitMethod(joinPoint, result, lengthMillis);
    return result;
  }

  /**
   * 退出方法
   *
   * @param joinPoint
   */
  private static void enterMethod(JoinPoint joinPoint)
  {
    //获取连接点的签名
    CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
    //回去当前连接点所属的类
    Class<?> cls = codeSignature.getDeclaringType();
    //获取方法的名称
    String methodName = codeSignature.getName();
    //获取方法的参数的名称
    String[] parameterNames = codeSignature.getParameterNames();
    //获取方法的值
    Object[] parameterValues = joinPoint.getArgs();
    //初始化一个StringBuilder，用于保存打印的数据,包含各个参数的名称和值
    StringBuilder builder = new StringBuilder("\u21E2 ");
    builder.append("Class=" + cls.getName() + ",");
    builder.append(methodName).append('(');
    for (int i = 0; i < parameterValues.length; i++)
    {
      if (i > 0)
      {
        builder.append(", ");
      }
      builder.append(parameterNames[i]).append('=');
      builder.append(Strings.toString(parameterValues[i]));
    }
    builder.append(')');
    //判断当前的线程是不是主线程，如果不是的话打印当前的线程
    if (Looper.myLooper() != Looper.getMainLooper())
    {
      builder.append(" [Thread:\"").append(Thread.currentThread().getName()).append("\"]");
    }
    Log.d("chenTest", builder.toString());
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
    {
      final String section = builder.toString().substring(2);
      Trace.beginSection(section);
    }
  }

  /**
   * 突出方法
   *
   * @param joinPoint    连接点
   * @param result       结果
   * @param lengthMillis
   */
  private static void exitMethod(JoinPoint joinPoint, Object result, long lengthMillis)
  {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
    {
      Trace.endSection();
    }
    Signature signature = joinPoint.getSignature();
    Class<?> cls = signature.getDeclaringType();
    String methodName = signature.getName();
    boolean hasReturnType = signature instanceof MethodSignature && ((MethodSignature) signature).getReturnType() != void.class;

    StringBuilder builder = new StringBuilder("\u21E0 ")
            .append("Class=")
            .append(cls.getName())
            .append(",")
            .append(methodName)
            .append(" [")
            .append(lengthMillis)
            .append("ms]");

    if (hasReturnType)
    {
      builder.append(" = ");
      builder.append(Strings.toString(result));
    }
    Log.d("chenTest", builder.toString());
  }

}