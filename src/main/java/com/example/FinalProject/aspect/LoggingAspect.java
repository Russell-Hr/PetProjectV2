package com.example.FinalProject.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LogManager.getLogger(LoggingAspect.class);
    @Pointcut("execution(* *..show*(..))")
    public void loggingPointcut() {
    }

    @Before("loggingPointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("Beforre method" + name);

    }

    @AfterReturning("loggingPointcut()")
    public void afterReturningAdvice() {
        log.info("After Returning");
    }
}
