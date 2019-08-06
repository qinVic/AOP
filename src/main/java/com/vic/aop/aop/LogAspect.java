package com.vic.aop.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * AOP日志打印
 * @author qinquan
 * @date 2019/7/11
 */
@Component
@Aspect
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution (* com.vic.aop.controller..*.*(..))")
    public void logAop(){}

    @Around("logAop()")
    public Object aroundAop(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("【项目日志打印】======{}.{}() start ======,参数:\n{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), argsToString(joinPoint.getArgs()));
        DateTime startTime = new DateTime();
        DateTime endTime = null;
        Interval interval = null;
        Object response = null;
        try {
            // 执行的方法
            response = joinPoint.proceed();
        } catch (Exception e) {
            endTime = new DateTime();
            interval = new Interval(startTime, endTime);
            LOGGER.error("【项目日志打印】======{}.{}() end ======,响应时间:{}毫秒", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), interval.toDurationMillis());
            throw e;
        }
        endTime = new DateTime();
        interval = new Interval(startTime, endTime);
        LOGGER.info("【项目日志打印】======{}.{}() end ======,响应时间:{}毫秒,响应内容:{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), interval.toDurationMillis(), argsToString(response));
        return response;
    }

    private String argsToString(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            LOGGER.error(" ", e.getMessage());
        }
        return String.valueOf(obj);
    }
}
