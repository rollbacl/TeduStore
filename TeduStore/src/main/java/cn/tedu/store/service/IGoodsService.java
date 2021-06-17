package cn.tedu.store.service;

import cn.tedu.store.bean.Goods;

import java.util.List;

public interface IGoodsService {
	/**
	 * 查询商品列表时默认的排序方式
	 */
	String ORDER_BY_DEFAULT = "priority DESC";
	/**
	 * 查询商品列表时默认的排序方式
	 */
	String ORDER_BY_PRICE_ASC = "price ASC";
	/**
	 * 查询商品列表时默认的排序方式
	 */
	String ORDER_BY_PRICE_DESC = "price DESC";
	
	/**
	 * 排序方案
	 */
	String[] ORDER_BY = {
			ORDER_BY_DEFAULT,
			ORDER_BY_PRICE_ASC, 
			ORDER_BY_PRICE_DESC
	};
	
	/**
	 * 每页显示多少条数据
	 */
	Integer COUNT_PER_PAGE = 20;
	
	/**
	 * 设置每页显示的商品数量
	 * @param countPerPage 每页显示的商品数量
	 */
	void setCountPerPage(int countPerPage);
	
	/**
	 * 获取每页显示的商品数量
	 * @return 每页显示的商品数量
	 */
	Integer getCountPerPage();

	/**
	 * 根据商品分类获取商品列表
	 * 
	 * @param categoryId
	 *            商品分类的ID
	 * @param orderBy
	 *            排序方式，值为SQL代码
	 * @param offset
	 *            偏移量，即跳过前面的多少条数据，如果从头开始获取数据，应该设置为0，如果不需要分页，该参数取null值即可
	 * @param count
	 *            获取数据的数量
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(
            Integer categoryId,
            String orderBy,
            Integer offset,
            Integer count);

	/**
	 * 根据商品分类获取商品列表，获取到的商品数据是按照优先级(priority)排序的
	 *
	 * @param categoryId
	 *            商品分类的ID
	 * @param offset
	 *            偏移量，即跳过前面的多少条数据，如果从头开始获取数据，应该设置为0，如果不需要分页，该参数取null值即可
	 * @param count
	 *            获取数据的数量
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(
            Integer categoryId,
            Integer offset,
            Integer count);

	/**
	 * 根据商品分类获取商品列表
	 *
	 * @param categoryId
	 *            商品分类的ID
	 * @param orderBy
	 *            排序方式，值为SQL代码
	 * @param page
	 *            获取第几页的数据
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(
            Integer categoryId,
            String orderBy,
            Integer page);

	/**
	 * 根据商品分类获取商品列表，按照默认的排序方式（根据优先级）进行排序
	 *
	 * @param categoryId
	 *            商品分类的ID
	 * @param page
	 *            获取第几页的数据
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(
            Integer categoryId,
            Integer page);

	/**
	 * 根据商品分类获取商品列表，只显示第1页的数据
	 *
	 * @param categoryId
	 *            商品分类的ID
	 * @param orderBy
	 *            排序方式，值为SQL代码
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(
            Integer categoryId,
            String orderBy);

	/**
	 * 根据商品分类获取商品列表，按照默认的排序方式（根据优先级）进行排序，只显示第1页的数据
	 *
	 * @param categoryId
	 *            商品分类的ID
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(
            Integer categoryId);

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
