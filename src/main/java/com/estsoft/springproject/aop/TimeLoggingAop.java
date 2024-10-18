package com.estsoft.springproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 메서드 수행 시간을 측정하기 위한 AOP 클래스
 */
@Aspect
@Component
public class TimeLoggingAop {

    /**
     * com.estsoft.springproject.book 패키지 내의 모든 메서드에 대해,
     * 메서드 호출 전후로 수행 시간을 측정
     *
     * @param joinPoint 실행되는 메서드의 정보가 담긴 객체
     * @return 메서드의 원래 결과를 리턴
     * @throws Throwable 메서드 실행 중 발생하는 예외를 던짐
     */
    @Around("execution(* com.estsoft.springproject.book..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTimeMs = System.currentTimeMillis();  // 시작 시간 기록
        System.out.println("START: " + joinPoint.toString());
        try {
            // 원래의 메서드 실행
            return joinPoint.proceed();
        } finally {
            // 메서드 종료 후 수행 시간 계산 및 출력
            long finishTimeMs = System.currentTimeMillis();
            long timeMs = finishTimeMs - startTimeMs;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

}
