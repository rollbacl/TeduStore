package cn.tedu.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor 
	implements HandlerInterceptor {

	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		// 日志
		System.out.println("LoginInterceptor.preHandle()");
		System.out.println("\tHttpServletRequest request : " + request);
		System.out.println("\tHttpServletResponse response : " + response);
		System.out.println("\tObject handler : " + handler);

		// 获取HttpSession对象
		HttpSession session
			= request.getSession();
		// 判断是否已登录
		if (session.getAttribute("uid") == null) {
			// 确定重定向到的页面：主页
			String url =
				request.getContextPath()
				+ "/main/index.do";
			// 没有登录，则执行重定向
			response.sendRedirect(url);
			// 拦截
			return false;
		}
		// 放行
		return true;
	}

	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView) throws Exception {
		// 日志
		System.out.println("LoginInterceptor.postHandle()");
		System.out.println("\tHttpServletRequest request : " + request);
		System.out.println("\tHttpServletResponse response : " + response);
		System.out.println("\tObject handler : " + handler);
		System.out.println("\tModelAndView modelAndView : " + modelAndView);
	}

	public void afterCompletion(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler, 
			Exception ex)
			throws Exception {
		// 日志
		System.out.println("LoginInterceptor.afterCompletion()");
		System.out.println("\tHttpServletRequest request : " + request);
		System.out.println("\tHttpServletResponse response : " + response);
		System.out.println("\tObject handler : " + handler);
		System.out.println("\tException ex : " + ex);
	}

}
