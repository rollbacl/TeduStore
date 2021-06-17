package cn.tedu.store.mapper;

import cn.tedu.store.bean.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
	
	/**
	 * 根据商品分类获取商品列表
	 * @param categoryId	商品分类的ID
	 * @param orderBy		排序方式，值为SQL代码
	 * @param offset		偏移量，即跳过前面的多少条数据，如果从头开始获取数据，应该设置为0，如果不需要分页，该参数取null值即可
	 * @param count			获取数据的数量
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(
            @Param("categoryId") Integer categoryId,
            @Param("orderBy") String orderBy,
            @Param("offset") Integer offset,
            @Param("count") Integer count);

	/**
	 * 获取某分类的商品的数量
	 * @param categoryId 分类的id
	 * @return 商品的数量
	 */
	Integer getGoodsCountByCategoryId(
            Integer categoryId);

	/**
	 * 根据商品id获取商品信息
	 * @param id 商品id
	 * @return 匹配的商品信息，如果没有匹配的数据，则返回null
	 */
	Goods getGoodsById(Integer id);

	/**
	 * 根据商品的item_type获取商品列表，即：获取同类商品
	 * @param itemType 商品的类型
	 * @return 同类商品的List集合
	 */
	List<Goods> getGoodsListByItemType(
            String itemType);

}
