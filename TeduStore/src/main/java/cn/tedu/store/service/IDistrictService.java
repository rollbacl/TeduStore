package cn.tedu.store.service;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

import java.util.List;

public interface IDistrictService {
	/**
	 * 获取所有省的列表
	 * 
	 * @return 所有省的列表
	 */
	List<Province> getProvinces();

	/**
	 * 获取某个省的市的列表
	 * 
	 * @param provinceCode
	 *            省的编码
	 * @return 某个省的市的列表
	 */
	List<City> getCities(String provinceCode);

	/**
	 * 获取某个市的区的列表
	 * 
	 * @param provinceCode
	 *            市的编码
	 * @return 某个市的区的列表
	 */
	List<Area> getAreas(String cityCode);

	/**
	 * 根据省的编码获取省的名称
	 * 
	 * @param provinceCode
	 *            省的编码
	 * @return 省的名称
	 */
	String getProvinceNameByCode(String provinceCode);

	/**
	 * 根据市的编码获取市的名称
	 * 
	 * @param cityCode
	 *            市的编码
	 * @return 市的名称
	 */
	String getCityNameByCode(String cityCode);

	/**
	 * 根据区的编码获取区的名称
	 * 
	 * @param areaCode
	 *            区的编码
	 * @return 区的名称
	 */
	String getAreaNameByCode(String areaCode);
}
