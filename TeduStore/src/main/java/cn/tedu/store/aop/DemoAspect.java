package cn.tedu.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面组件 
 */
@Component
@Aspect //来自 AspectJ的注解
public class DemoAspect {
	
	//来自 AspectJ的注解
	//在 userService的全部方法之前执行
	//切面组件的demo方法
	@Before("bean(userService)") 
	public void demo(){
		System.out.println("Hello World!"); 
	}

	//在目标方法之后执行
	@After("bean(userService)") 
	public void test1(){
		System.out.println("test1");
	}
	
	//在目标方法没有异常以后执行
	@AfterReturning("bean(userService)") 
	public void test2(){
		System.out.println("test2");
	}
	
	//在目标方法出现异常以后执行
	@AfterThrowing("bean(userService)") 
	public void test3(){
		System.out.println("test3");
	}
	
	//环绕通知:
	//1. 方法必须有返回值
	//2. 方法必须有参数ProceedingJoinPoint
	//   称为处理连接点
	//3. 在方法中调用 jp.proceed() 就在执行目标的
	//   业务方法, 返回值就是目标方法的执行结果
	//4. jp.proceed() 方法执行出现异常, 就是
	//   目标业务方法执行期间出现异常
	@Around("bean(userService)")
	public Object test4(
			ProceedingJoinPoint jp)
		throws Throwable{
		System.out.println("开始");
		Object val = jp.proceed();
		System.out.println("结束:"+val); 
		return val;
	}
	
	
}






