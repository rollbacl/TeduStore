package cn.tedu.store.controller;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

	@Resource(name="goodsCategoryService")
	private IGoodsCategoryService goodsCategoryService;
	
	@Resource(name="goodsService")
	private IGoodsService goodsService;
	
	@RequestMapping("/index.do")
	public String showIndex(ModelMap modelMap) {
		// 声明电脑分类的数据
		List<List<GoodsCategory>> 
			computerCategories 
				= new ArrayList<List<GoodsCategory>>();
		// 声明电脑分类中排名前3的商品
		List<Goods> computers;

		// 获取161的3个2级分类：
		List<GoodsCategory> categories161 = goodsCategoryService.getGoodsCategoryListByParentId(161, 0, 3);
		// computerCategories.add(categories);

		// 获取各2级分类的3级分类
		List<GoodsCategory> subCategories;
		for (GoodsCategory goodsCategory : categories161) {
			subCategories = goodsCategoryService.getGoodsCategoryListByParentId(goodsCategory.getId(), null, null);
			computerCategories.add(subCategories);
		}
		
		// 获取电脑分类中排名前3的商品列表
		computers = goodsService.getGoodsListByCategoryId(163, 0, 3);

		// 测试输出数据
		System.out.println("MainController.showIndex()");
		System.out.println("\t电脑的3个二级分类：");
		for (GoodsCategory category : categories161) {
			System.out.println("\t" + category.getName());
		}
		System.out.println();
		System.out.println("\t电脑的3个二级分类对应的三级分类：");
		for (List<GoodsCategory> testCategories : computerCategories) {
			for (GoodsCategory goodsCategory : testCategories) {
				System.out.println("\t" + goodsCategory.getName());
			}
			System.out.println();
		}
		System.out.println("\t电脑分类中排名前3的商品的列表");
		for (Goods goods : computers) {
			System.out.println("\t" + goods);
		}

		// 封装需要转发的数据
		modelMap.addAttribute(
			"categories161",
			categories161);
		modelMap.addAttribute(
				"computerCategories",
				computerCategories);
		modelMap.addAttribute(
				"computers",
				computers);
		
		// 执行转发
		return "index";
	}

}
