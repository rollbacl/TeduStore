package cn.tedu.store.service;

import cn.tedu.store.bean.Cart;

import java.util.List;

public interface ICartService {

	/**
	 * 将商品加入到购物车
	 * @param cart 购物车信息
	 * @return 新增加的记录的ID
	 */
	Integer add(Cart cart);
	
	/**
	 * 获取记录的数量
	 * @param uid 用户id
	 * @param goodsId 商品id
	 * @return 记录的数量，由于业务层应该维护对应的业务逻辑，返回值应该是0或1。
	 */
	Integer getRecordCount(
            Integer uid,
            Integer goodsId);

	/**
	 * 调整购物车中某商品数量
	 * @param uid 用户id
	 * @param goodsId 商品id
	 * @param amount 调整值，可以是正数，也可以是负数
	 * @return 受影响的行数
	 */
	Integer changeGoodsCount(
            Integer uid,
            Integer goodsId,
            Integer amount);
	
	/**
	 * 获取购物车中的商品列表
	 * @param uid 用户id
	 * @return 购物车中的商品列表
	 */
	List<Cart> getCartList(Integer uid);
	
	/**
	 * 根据id获取购物车的商品信息
	 * @param uid 用户id
	 * @param id 购物车中的数据id
	 * @return 获取到的信息
	 */
	Cart getCartById(Integer uid, Integer id);
	
}
