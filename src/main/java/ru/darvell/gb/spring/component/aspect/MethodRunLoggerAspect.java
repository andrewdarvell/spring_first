package ru.darvell.gb.spring.component.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MethodRunLoggerAspect {

    @Before("@annotation(ru.darvell.gb.spring.annotation.MethodRunLogger)")
    public void methodRunLogger(JoinPoint joinPoint) {
        log.info("running method: {}", joinPoint.getSignature());
    }
}
