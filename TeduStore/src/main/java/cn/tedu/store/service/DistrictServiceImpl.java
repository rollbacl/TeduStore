package cn.tedu.store.service;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.mapper.DistrictMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("districtService")
public class DistrictServiceImpl 
	implements IDistrictService {
	
	@Resource
	private RedisTemplate<String, Object> 
		redisTemplate;
	
	@Resource(name="districtMapper")
	private DistrictMapper districtMapper;

	public List<Province> getProvinces() {
		//先在Redis中查询, 如果没有就查询数据库
		//并且将数据查询结果保存到Redis中
		ValueOperations<String, Object> ops= 
			redisTemplate.opsForValue();
	 	List<Province> list=
	 		(List<Province>)ops
	 		.get("province-list");
	 	if(list==null){
	 		System.out.println("查询数据库Province");
	 		list=districtMapper.getProvinces();
	 		ops.set("province-list", list,
	 				1, TimeUnit.DAYS);
	 	}
		return list;
	}

	public List<City> getCities(String provinceCode) {
		return districtMapper.getCities(provinceCode);
	}

	public List<Area> getAreas(String cityCode) {
		return districtMapper.getAreas(cityCode);
	}

	public String getProvinceNameByCode(String provinceCode) {
		return districtMapper.getProvinceNameByCode(provinceCode);
	}

	public String getCityNameByCode(String cityCode) {
		return districtMapper.getCityNameByCode(cityCode);
	}

	public String getAreaNameByCode(String areaCode) {
		return districtMapper.getAreaNameByCode(areaCode);
	}

}
