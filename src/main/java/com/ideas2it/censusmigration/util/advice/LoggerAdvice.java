package com.ideas2it.censusmigration.util.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ideas2it.censusmigration.util.Logger.CustomLogger;

@Aspect
@Component
public class LoggerAdvice {

    @Around("execution(* com.ideas2it.censusmigration.controller.*.*(..)) ||" +
            "execution(* com.ideas2it.censusmigration.service.*.*(..))" +
            "execution(* com.ideas2it.censusmigration.repository.*.*(..)) ||")
    public Object LogTheFlow(ProceedingJoinPoint joinPoint){
        Object result;
        CustomLogger logger = new CustomLogger(joinPoint.getSignature().getClass());
        logger.info("Inside the " +joinPoint.getSignature().getName()
                + joinPoint.getSignature().getDeclaringType());
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
        logger.info("OutSide the "+joinPoint.getSignature().getName()
                + joinPoint.getSignature().getDeclaringType());
        return result;
    }

}
