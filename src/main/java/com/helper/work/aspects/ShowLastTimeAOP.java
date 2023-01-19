package com.helper.work.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class ShowLastTimeAOP {

    public long time;

    @Around("@annotation(ShowLastTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        time = Instant.now().getEpochSecond();
        final Object proceed = joinPoint.proceed();
        System.out.println("Method terminated in " + (time - Instant.now().getEpochSecond()) + " seconds");
        return proceed;
    }

    @Before("execution(* *(..))")
    public void getAdvice() {
    }

    @After("execution(* *(..))")
    public void show() {
    }
}
