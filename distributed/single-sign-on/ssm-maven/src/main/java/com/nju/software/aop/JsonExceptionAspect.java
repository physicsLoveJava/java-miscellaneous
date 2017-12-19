package com.nju.software.aop;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.nju.software.util.JsonResult;

@Aspect
@Component
public class JsonExceptionAspect {
	
	private static Logger logger = Logger.getLogger(JsonExceptionAspect.class);

	@Pointcut("execution(* com.nju.software.web.controller.api..json_*(..))")
	public void apiMethods(){
		
	}
	
	@Around("apiMethods()")
	public Object apiExceptions(ProceedingJoinPoint pjp){
		try {
			Object retVal = pjp.proceed();
			return retVal;
		} catch (Exception e) {
			logger.error(getMethodName(pjp) + "抛出异常", e);
			try {
				JsonResult.toJson(getResponse(pjp), JsonResult.useDefault(false).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	private HttpServletResponse getResponse(ProceedingJoinPoint pjp) {
		Object[] args = pjp.getArgs();
		HttpServletResponse response = (HttpServletResponse) args[args.length - 1];
		return response;
	}

	private String getMethodName(ProceedingJoinPoint pjp) {
		return pjp.getTarget().getClass().getName() + "[" + pjp.getSignature().getName() + "]";
	}
}
