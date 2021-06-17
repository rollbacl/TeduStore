package cn.tedu.store.controller;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IDistrictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/dict")
public class DictController {
	
	@Resource(name="districtService")
	private IDistrictService districtService;

	@RequestMapping("/provinces.do")
	@ResponseBody
	public ResponseResult<List<Province>> 
		getProvinces() {
		// 声明返回值
		ResponseResult<List<Province>> rr;
		
		// 获取数据
		List<Province> provinces
			= districtService.getProvinces();
		
		// 创建返回值
		rr = new ResponseResult<List<Province>>(
				1, provinces);
		
		// 返回
		return rr;
	}
	
	@RequestMapping("/cities.do")
	@ResponseBody
	public ResponseResult<List<City>>
		getCities(String provinceCode) {
		// 声明返回值
		ResponseResult<List<City>> rr;
		
		// 获取数据
		List<City> cities 
			= districtService
				.getCities(provinceCode);
		
		// 创建返回值
		rr = new ResponseResult<List<City>>(
				1, cities);
		
		// 返回
		return rr;
	}
	
	@RequestMapping("/areas.do")
	@ResponseBody
	public ResponseResult<List<Area>>
		getAreas(String cityCode) {
		// 声明返回值
		ResponseResult<List<Area>> rr;
		
		// 获取数据
		List<Area> areas 
			= districtService.getAreas(cityCode);
		
		// 创建返回值
		rr = new ResponseResult<List<Area>>(1, areas);
		
		// 返回
		return rr;
	}
	
}
