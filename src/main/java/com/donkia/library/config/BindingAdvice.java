package com.donkia.library.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Component
@Aspect
public class BindingAdvice {

    //@Before : 메소드 호출 전
    //@After : 메소드 호출 후
    @Around("execution(* com.donkia.library.*.*Controller.*(..))") //메서드 앞, 뒤
    public void validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        proceedingJoinPoint.proceed();
        stopWatch.stop();

        log.info("실행메서드 : " + methodName + ", 실행시간 : " + stopWatch.getTotalTimeMillis() +"ms");
        //return proceedingJoinPoint.proceed();
    }
}
