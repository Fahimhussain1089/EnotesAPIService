package com.hussain.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hussain.exception.GlobalExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


//	@Before("execution(* com.becoder.controller..*(..))")
//	public void beforeController(JoinPoint joinPoint)
//	{
//		Signature signature = joinPoint.getSignature();
//		String className = signature.getDeclaringType().getSimpleName();
//		String methodName = signature.getName();
//		log.info("Calling :: {} :: {}()",className,methodName);
//	}
//	
//	@After("execution(* com.becoder.controller..*(..))")
//	public void afterController(JoinPoint joinPoint)
//	{
//		Signature signature = joinPoint.getSignature();
//		String className = signature.getDeclaringType().getSimpleName();
//		String methodName = signature.getName();
//		log.info("End Calling :: {} :: {}()",className,methodName);
//	}
	
	@Around("execution(* com.hussain.controller..*(..))")//ye pehle chalega
	public Object jointPointController(ProceedingJoinPoint joinPoint) throws Throwable
	{
		Signature signature = joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();
		String methodName = signature.getName();
		log.info("Calling :: {} :: {}()",className,methodName);
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long duration = System.currentTimeMillis()-start;
		log.info("Exceution End :: {} :: {}() ::{} ms",className,methodName,duration);
		return result;
	}
	
	@Around("execution(* com.hussain.service..*(..))")
	public Object jointPointService(ProceedingJoinPoint joinPoint) throws Throwable
	{
		Signature signature = joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();
		String methodName = signature.getName();
		log.info("Calling :: {} :: {}()",className,methodName);
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long duration = System.currentTimeMillis()-start;
		log.info("Exceution End :: {} :: {}() ::{} ms",className,methodName,duration);
		return result;
	}
	
	
}
