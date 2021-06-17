package cn.tedu.store.service;

import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;
import cn.tedu.store.service.ex.OrderCreationException;

import java.util.List;

public interface IOrderService {

	/**
	 * 创建订单
	 * @param order 订单数据
	 * @param orderItems 订单中的商品数据
	 * @throws OrderCreationException 创建订单失败的异常
	 */
	void createOrder(
            Order order,
            List<OrderItem> orderItems);
}
