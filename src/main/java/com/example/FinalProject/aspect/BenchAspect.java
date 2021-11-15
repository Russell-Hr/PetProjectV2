package com.example.FinalProject.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BenchAspect {
    private static final Logger log = LogManager.getLogger(BenchAspect.class);
   @Pointcut("@annotation(com.example.FinalProject.annotation.Bench)")
public void benchPointcut(){
   }
    @Around("benchPointcut()")
   public Object aroundAdvice (ProceedingJoinPoint joinPoint) {
       try {
          long beforeMethodInvocation = System.nanoTime();
          Object returnValue= joinPoint.proceed();
          long afterMethodInvocation = System.nanoTime();
          log.info("Execution - " + (afterMethodInvocation - beforeMethodInvocation)/1000000 + " ms");
          return returnValue;
       } catch (Throwable e) {
           log.info("After Throwing");
          throw new IllegalArgumentException("Method" + joinPoint.getSignature().getName()+ "caused an Exception", e );
       }
    }
}
