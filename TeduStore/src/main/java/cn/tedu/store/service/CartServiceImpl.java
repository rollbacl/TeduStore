package cn.tedu.store.service;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ex.DataNotFoundException;
import cn.tedu.store.service.ex.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cartService")
public class CartServiceImpl
	implements ICartService {

	@Resource(name="cartMapper")
	private CartMapper cartMapper;

	public Integer add(Cart cart) {
		// 先查询对应的数据
		Integer count = getRecordCount(
				cart.getUid(), 
				cart.getGoodsId());
		// 判断该用户的购物车中是否已经有该商品
		if (count == 0) {
			// 该用户的购物车中尚无该商品
			// 则在购物中添加新数据
			Integer affectedRows 
				= cartMapper.add(cart);
			if (affectedRows == 1) {
				return 1;
			} else {
				throw new ServiceException(
					"将商品添加到购物车时出现未知错误，请联系管理员！");
			}
		} else {
			// 该用户的购物车已经有该商品
			// 则增加数量即可
			changeGoodsCount(
					cart.getUid(), 
					cart.getGoodsId(), 
					cart.getGoodsCount());
			return 1;
		}
		
	}

	public Integer getRecordCount(Integer uid, Integer goodsId) {
		return cartMapper.getRecordCount(uid, goodsId);
	}

	public Integer changeGoodsCount(
			Integer uid, 
			Integer goodsId, 
			Integer amount) {
		Integer affectedRows = 
			cartMapper.changeGoodsCount(
				uid, goodsId, amount);
		if (affectedRows != 1) {
			throw new DataNotFoundException(
				"购物车数据有误！请刷新后再次尝试！");
		}
		return affectedRows;
	}

	public List<Cart> getCartList(Integer uid) {
		return cartMapper.getCartList(uid);
	}

	public Cart getCartById(
			Integer uid, Integer id) {
		return cartMapper.getCartById(uid, id);
	}
	
}
