package cn.tedu.store.service;

import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.ex.OrderCreationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl {
	
	@Resource(name="orderMapper")
	private OrderMapper orderMapper;
	
	@Transactional
	public void createOrder(
		Order order, List<OrderItem> orderItems) {
		// 使用变量获取每次操作的返回值
		Integer affectedRows;
		// 创建订单失败时的提示文字
		String exceptionMessage = "创建订单失败！";
		// 增加“订单”
		affectedRows = 
			orderMapper.insertOrder(order);
		// 判断增加“订单”是否成功
		if (affectedRows != 1) {
			throw new OrderCreationException(
				exceptionMessage);
		}
		// 增加“订单商品”
		for (OrderItem orderItem : orderItems) {
			// 执行增加，并获取返回值
			affectedRows = orderMapper
				.insertOrderItem(orderItem);
			// 判断增加“订单商品”是否成功
			if (affectedRows != 1) {
				throw new OrderCreationException(
					exceptionMessage);
			}
		}
	}

}
