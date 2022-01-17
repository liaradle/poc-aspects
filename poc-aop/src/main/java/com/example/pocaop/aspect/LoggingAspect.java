package com.example.pocaop.aspect;

import com.example.pocaop.data.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
    private static final Logger log = LogManager.getLogger(LoggingAspect.class);
//
//    @Around("execution(* com.example.pocaop.service..*(..)))")
//    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//
//        String className = methodSignature.getDeclaringType().getSimpleName();
//        String methodName = methodSignature.getName();
//
//        final StopWatch stopWatch = new StopWatch();
//
//        stopWatch.start();
//        Object result = proceedingJoinPoint.proceed();
//        stopWatch.stop();
//        log.info("Execution time of " + className + "." + methodName + "::" + stopWatch.getTotalTimeMillis() +"ms");
//
//        return result;
//    }
//
//
//
//    @After("execution(* com.example.pocaop.service..*(..)))")
//    public Object censoring(JoinPoint joinPoint) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//
//        String methodName = methodSignature.getName();
//
//        if(methodName.equals("getEmailById")) {
//            return "REDACTED";
//        }
//        else {
//            System.out.println("ye");
//            return joinPoint.getThis();
//        }
//    }


    @Before("execution(String com.example.pocaop.service.EmployeeManager.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("Intercepted method calls {}", joinPoint.getSignature());
        log.info("Censoring email address for log...");
    }

    @Around(value="execution(* com.example.pocaop.service.EmployeeManager.*(..))")
    public void censorEmailAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String email = "REDACTED";
        if(joinPoint.getSignature().getName().equals("getEmailById")) {
            log.info("Censored email request. New value for email is {}", email);
            return;
        }
        Employee employee = (Employee) joinPoint.proceed();
        employee.setEmail(email);
        log.info("Censored employee request. New value for employee is {}", employee);
    }

    @AfterReturning(value="execution(* com.example.pocaop.service.EmployeeManager.*(..))",
    returning="result")
    public void after(JoinPoint joinPoint, Object result) {
        log.info("{} returned with value {}", joinPoint, result);
    }

}
