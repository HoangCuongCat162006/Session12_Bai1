package org.example.session12_bai1.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    // BEFORE
    // Log trước khi controller chạy
    @Before("execution(* com.example.library.controller.*.*(..))")
    public void logBefore(org.aspectj.lang.JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        System.out.println("======== BEFORE ========");

        System.out.println("Method: " + methodName);

        System.out.println("Arguments: " + Arrays.toString(args));
    }

    // AFTER RETURNING
    // Log kết quả trả về của service
    @AfterReturning(
            pointcut = "execution(* com.example.library.service.impl.*.*(..))",
            returning = "result"
    )
    public void logAfterReturning(org.aspectj.lang.JoinPoint joinPoint,
                                  Object result) {

        String methodName = joinPoint.getSignature().getName();

        System.out.println("======== AFTER RETURNING ========");

        System.out.println("Method: " + methodName);

        System.out.println("Result: " + result);
    }

    // AROUND
    // Đo thời gian thực thi method controller
    @Around("execution(* com.example.library.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        System.out.println("======== AROUND ========");

        System.out.println("Method: " + joinPoint.getSignature().getName());

        System.out.println("Execution time: "
                + (endTime - startTime)
                + " ms");

        return result;
    }
}
