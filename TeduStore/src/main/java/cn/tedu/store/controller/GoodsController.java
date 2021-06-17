package cn.tedu.store.controller;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController 
	extends BaseController {
	
	@Resource(name="goodsService")
	private IGoodsService goodsService;
	
	@RequestMapping("/list.do")
	public String showGoodsListByCategoryId(
		@RequestParam(value="category_id", required=true) Integer categoryId,
		@RequestParam(value="order_by", required=false) Integer orderBy,
		@RequestParam(value="page", required=false) Integer page,
		ModelMap modelMap) {
		// 声明需要转发的数据
		List<Goods> goodsList;
		Integer goodsCount;
		Integer pages;
		Integer countPerPage;

		// 判断参数
		String orderByStr;
		if (categoryId == null || categoryId < 1) {
			modelMap.addAttribute("msg", "请求参数有误！");
			return "error";
		}
		
		// 获取数据
		goodsCount = goodsService
				.getGoodsCountByCategoryId(
						categoryId);
		
		// 判断参数
		if (page == null || page < 1) {
			page = 1;
		}
		if (orderBy == null || orderBy < 0 ||
				orderBy >= IGoodsService.ORDER_BY.length) {
			orderBy = 0;
		}
		orderByStr = IGoodsService.ORDER_BY[orderBy];
		
		// 设置每页显示多少条商品
		goodsService.setCountPerPage(
				IGoodsService.COUNT_PER_PAGE);

		// 获取数据
		goodsList = goodsService
				.getGoodsListByCategoryId(
					categoryId, orderByStr, page);
		
		// 获取每页显示的商品数量
		countPerPage = goodsService
				.getCountPerPage();
		// 计算总页数
		pages = goodsCount / countPerPage;
		pages += goodsCount % countPerPage == 0 ? 0 : 1;

		// 测试在控制台输出以上goodsList
		System.out.println("GoodsController.getGoodsListByCategoryId()");
		System.out.println("\tcategoryId=" + categoryId);
		System.out.println("\tgoodsCount=" + goodsCount);
		System.out.println("\tcountPerPage=" + countPerPage);
		System.out.println("\tpages=" + pages);
		System.out.println("\tpage=" + page);
		for (Goods goods : goodsList) {
			System.out.println("\t" + goods);
		}
		
		// 封装需要转发的数据
		modelMap.addAttribute("goodsList", goodsList);
		modelMap.addAttribute("goodsCount", goodsCount);
		modelMap.addAttribute("countPerPage", countPerPage);
		modelMap.addAttribute("pages", pages);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("categoryId", categoryId);

		// 执行转发
		return "goods_list"; // jsp文件的文件名
	}
	
	@RequestMapping("/details.do")
	public String showGoodsDetails(
		@RequestParam(value="id", required=true) Integer id, 
		ModelMap modelMap) {
		// 声明需要转发到JSP的数据
		Goods goods;

		// 获取需要转发到JSP的数据
		goods = goodsService.getGoodsById(id);
		
		// 测试获取到的数据
		System.out.println("GoodsController.showGoodsDetails()");
		System.out.println("\tgoods id=" + id);
		System.out.println("\tgoods=" + goods);

		// 判断数据
		if (goods != null) {
			// 获取到数据后，还需要获取同类型商品的列表
			List<Goods> goodsList = goodsService
				.getGoodsListByItemType(
					goods.getItemType());
			
			// 测试输出
			System.out.println("\tgoodsList：");
			for (Goods goods2 : goodsList) {
				System.out.println("\t > " + goods2.getId() + ", " + goods2.getTitle());
			}
			
			// 封装需要转发到JSP的数据
			modelMap.addAttribute("goods", goods);
			modelMap.addAttribute("goodsList", goodsList);
			// 执行转发
			return "goods_details";
		} else {
			// 没有获取到数据
			return "error";
		}
	}

}
