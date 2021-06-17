package cn.tedu.store.service;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl 
	implements IGoodsService {

	@Resource(name="goodsMapper")
	private GoodsMapper goodsMapper;
	
	private Integer countPerPage
		= COUNT_PER_PAGE;
	
	public void setCountPerPage(int countPerPage) {
		if (countPerPage >= 5 
				&& countPerPage <= 30) {
			this.countPerPage = countPerPage;
		}
	}
	
	public Integer getCountPerPage() {
		return this.countPerPage;
	}

	public List<Goods> 
		getGoodsListByCategoryId(
			Integer categoryId, 
			String orderBy, 
			Integer offset, 
			Integer count) {
		return goodsMapper
				.getGoodsListByCategoryId(
					categoryId, orderBy, 
					offset, count);
	}

	public List<Goods> 
		getGoodsListByCategoryId(
			Integer categoryId, 
			Integer offset, 
			Integer count) {
		return getGoodsListByCategoryId(
				categoryId, 
				ORDER_BY_DEFAULT, 
				offset, count);
	}

	public List<Goods> getGoodsListByCategoryId(
			Integer categoryId, 
			String orderBy, 
			Integer page) {
		Integer offset = (page - 1) * getCountPerPage();
		Integer count = getCountPerPage();
		return getGoodsListByCategoryId(
				categoryId, 
				orderBy, 
				offset, 
				count);
	}

	public List<Goods> getGoodsListByCategoryId(
			Integer categoryId, 
			Integer page) {
		return getGoodsListByCategoryId(
				categoryId, 
				ORDER_BY_DEFAULT, 
				page);
	}

	public List<Goods> getGoodsListByCategoryId(
			Integer categoryId, 
			String orderBy) {
		return getGoodsListByCategoryId(
				categoryId, 
				orderBy, 
				1);
	}

	public List<Goods> getGoodsListByCategoryId(
			Integer categoryId) {
		return getGoodsListByCategoryId(
				categoryId, 
				ORDER_BY_DEFAULT, 
				1);
	}

	public Integer getGoodsCountByCategoryId(
			Integer categoryId) {
		return goodsMapper
				.getGoodsCountByCategoryId(
					categoryId);
	}

	public Goods getGoodsById(Integer id) {
		return goodsMapper.getGoodsById(id);
	}

	public List<Goods> getGoodsListByItemType(
			String itemType) {
		return goodsMapper
				.getGoodsListByItemType(
					itemType);
	}

}
