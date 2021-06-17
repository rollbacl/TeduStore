package cn.tedu.store.service;

import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl
	implements IGoodsCategoryService {
	
	@Resource(name="goodsCategoryMapper")
	private GoodsCategoryMapper goodsCategoryMapper;

	public List<GoodsCategory> getGoodsCategoryListByParentId(Integer parentId, Integer offset, Integer count) {
		return goodsCategoryMapper.getGoodsCategoryListByParentId(parentId, offset, count);
	}

}
