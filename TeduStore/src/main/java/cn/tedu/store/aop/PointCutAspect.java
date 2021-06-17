package cn.tedu.store.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 切入点表达式演示 
 */
@Component
@Aspect
public class PointCutAspect {
	
	@Before("within(cn.tedu.store..*Impl)")
	public void test1(){
		System.out.println("Point Cut 1");
	}
	
	@Before("execution(* cn.tedu.store..IUserService.login(..))")
	public void test2(){
		System.out.println("Point Cut 2");
	}
}








