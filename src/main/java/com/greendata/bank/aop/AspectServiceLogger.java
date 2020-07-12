package com.greendata.bank.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectServiceLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectServiceLogger.class);

    @Before("within(com.greendata.bank.service..*)")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("Before execution {}", joinPoint);
    }

    @AfterReturning(value = "within(com.greendata.bank.service..*)",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info("{} returned with value {}", joinPoint, result);
    }

    @After(value = "within(com.greendata.bank.service..*)")
    public void after(JoinPoint joinPoint) {
        LOGGER.info("After execution of {}", joinPoint);
    }

    @AfterThrowing(pointcut = "within(com.greendata.bank.service..*)", throwing = "ex")
    public void logError(Exception ex) {
        LOGGER.error("ERROR: ", ex);
    }
}
