package com.kuaishou.raymond.algorithm.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: raymond
 * @Datetime: 2023/8/17 15:17
 * Description: 打点日志注解切面
 */
@Slf4j
@Aspect
@Component
public class PerfLogAspect {

    @Pointcut("@annotation(com.kuaishou.raymond.algorithm.demo.aspect.PerfLog)")
    public void perfLogAnnotation() {
    }

    @Around("perfLogAnnotation()")
    public Object aroundExecution(ProceedingJoinPoint pj) throws Throwable {
        log.info("Around is working...");
        return pj.proceed();
    }

    @Before("perfLogAnnotation()")
    public void beforeExecution(JoinPoint joinPoint) {
        log.info("Method execution started: {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning("perfLogAnnotation()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("Method executed successfully: {}", joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "perfLogAnnotation()", throwing = "ex")
    public void afterMethodException(JoinPoint joinPoint, Exception ex) {
        log.error("Method execution failed: {}, exception message = {}", joinPoint.getSignature().toShortString(), ex.getMessage());
    }
}
