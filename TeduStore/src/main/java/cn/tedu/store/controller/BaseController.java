package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

public abstract class BaseController {
	/**
	 * 从Session中获取当前登录的用户ID
	 * 
	 * @param session
	 * @return 如果Session存在用户ID，则返回用户ID，否则返回null。
	 */
	protected Integer getUidFromSession(HttpSession session) {
		// 从Session中获取当前登录的用户ID
		Object uidObject = session.getAttribute("uid");
		// 判断Session中的用户ID
		if (uidObject == null) {
			return null;
		} else {
			return Integer.valueOf(uidObject.toString());
		}
	}

}