package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeCostLogAdvice {

	private final Logger logger = LoggerFactory.getLogger(TimeCostLogAdvice.class);

	@Pointcut(value = "@annotation(com.example.demo.annotation.TimeCost)")
//	@Pointcut(value = "execution(* com.example.demo.controller.*.*(..))")
	public void cutMethod() {
	}

	@Around(value = "cutMethod()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Long timeBegin = System.currentTimeMillis();
		Signature signature = joinPoint.getSignature();// 获取签名
		String methodName = signature.getName(); // 获取方法名
		Object[] objs = joinPoint.getArgs(); // 传入参数
		logger.info(methodName + " begin!");
		Object object = joinPoint.proceed();
		Long timeEnd = System.currentTimeMillis();
		logger.info(methodName + " end! cost time: " + (timeEnd - timeBegin) + "毫秒");
		return object;
	}

}
