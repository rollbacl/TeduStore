package cn.tedu.store.mapper;

import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;

public interface OrderMapper {

	/**
	 * 增加订单记录
	 * @param order 订单信息
	 * @return 受影响的行数
	 */
	Integer insertOrder(Order order);

	/**
	 * 增加订单中的商品的记录
	 * @param item 订单中的商品
	 * @return 受影响的行数
	 */
	Integer insertOrderItem(OrderItem item);
	
}
