package cn.tedu.store.controller;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController
	extends BaseController {
	
	@Resource(name="cartService")
	private ICartService cartService;
	
	@RequestMapping("/list.do")
	public String showList(
		HttpSession session,
		ModelMap modelMap) {
		// 获取uid
		Integer uid = getUidFromSession(session);
		
		// 获取数据
		List<Cart> carts
			= cartService.getCartList(uid);
		
		// 封装数据，以准备转发
		modelMap.addAttribute("carts", carts);
		
		// 转发
		return "cart_list";
	}
	
	@RequestMapping(value="/add.do",
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> 
		handleAddToCart(
			Cart cart,
			HttpSession session) {
		// 声明返回值
		ResponseResult<Void> rr;
		
		// 获取uid
		Integer uid = getUidFromSession(session);
		// 封装uid
		cart.setUid(uid);
		
		// 执行
		try {
			cartService.add(cart);
			rr = new ResponseResult<Void>(1, "加入购物车成功！");
		} catch (ServiceException e) {
			rr = new ResponseResult<Void>(0, e);
		}
		
		// 返回
		return rr;
	}

}
