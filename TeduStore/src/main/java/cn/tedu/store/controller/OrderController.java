package cn.tedu.store.controller;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.Cart;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController
	extends BaseController {
	
	@Resource(name="addressService")
	private IAddressService addressService;
	
	@Resource(name="cartService")
	private ICartService cartService;
	
	@RequestMapping("/confirm.do")
	public String handleConfirmOrder(
		HttpSession session,
		String cartId,
		ModelMap modelMap) {
		// ***** 获取用户的收货人地址列表 *****
		// 从session中获取uid
		Integer uid = getUidFromSession(session);
		// 根据uid获取收货人地址列表
		List<Address> addresses
			= addressService
				.getAddressListByUid(uid);
		// 测试输出
		System.out.println("OrderController.handleConfirmOrder()");
		System.out.println("\t加载当前用户的收货人地址列表：");
		for (Address address : addresses) {
			System.out.println("\t" + address);
		}
		System.out.println();
		
		// ***** 获取所勾选的购物车商品信息 *****
		// 声明集合，用于存储多个购物车商品信息
		List<Cart> carts = new ArrayList<Cart>();
		// 拆分cartId，即将"5,6,7"变成{"5","6","7"}
		String[] ids = cartId.split(",");
		// 声明Integer类型的id
		Integer id;
		// 声明每次查询到的购物车商品
		Cart cart;
		// 遍历拆分出来的数组
		for (int i = 0; i < ids.length; i++) {
			// 将遍历到的每个String类型的数组元素转成Integer
			id = Integer.valueOf(ids[i]);
			// 根据id获取购物车商品信息
			cart = cartService
				.getCartById(uid, id);
			// 将查询到的数据添加到集合中
			carts.add(cart);
		}
		// 测试输出
		System.out.println("\t加载当前用户的勾选的购物车商品列表：");
		for (Cart cart1 : carts) {
			System.out.println("\t" + cart1);
		}
		System.out.println();
		
		// ***** 封装数据，以准备转发 *****
		modelMap.addAttribute("addresses", addresses);
		modelMap.addAttribute("carts", carts);
		
		// 执行转发
		return "test";
	}

}
