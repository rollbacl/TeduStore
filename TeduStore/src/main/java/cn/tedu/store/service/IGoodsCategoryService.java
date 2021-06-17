package cn.tedu.store.service;

import cn.tedu.store.bean.GoodsCategory;

import java.util.List;

public interface IGoodsCategoryService {
	/**
	 * 根据父级分类ID获取商品分类列表
	 * 
	 * @param parentId
	 *            父级分类ID
	 * @param offset
	 *            偏移量，即跳过前面的多少条数据，如果从头开始获取数据，应该设置为0，如果不需要分页，该参数取null值即可
	 * @param count
	 *            获取数据的数量
	 * @return 商品分类列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<GoodsCategory> 
		getGoodsCategoryListByParentId(
            Integer parentId,
            Integer offset,
            Integer count);
}
