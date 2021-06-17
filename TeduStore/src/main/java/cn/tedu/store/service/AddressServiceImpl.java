package cn.tedu.store.service;

import cn.tedu.store.bean.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.ex.DataNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("addressService")
public class AddressServiceImpl
	implements IAddressService {
	
	@Resource
	private AddressMapper addressMapper;
	
	@Resource(name="districtService")
	private IDistrictService districtService;

	public Integer add(Address address) {
		// 由Controller提交的数据并没有recvDistrict字段
		// 此时需要得到该字段应有的值
		// 并封装到address中
		String recvDistrict 
			= getRecvDistrict(address);
		address.setRecvDistrict(recvDistrict);
		// 确定当前收货地址是否为默认地址
		// 仅当当前收货地址是该用户的第1条时设置为默认
		List<Address> addresses 
			= getAddressListByUid(
					address.getUid());
		Integer isDefault 
			= addresses.size() == 0 ? 1 : 0;
		address.setIsDefault(isDefault);
		// 执行增加
		addressMapper.insert(address);
		// 获取id
		Integer id = address.getId();
		// 返回
		return id;
	}

	public List<Address> getAddressListByUid(
			Integer uid) {
		return addressMapper
				.getAddressListByUid(uid);
	}

	public Address getAddressByIdAndUid(
			Integer id, Integer uid) {
		return addressMapper
				.getAddressByIdAndUid(
						id, uid);
	}

	@Transactional
	public Integer delete(
			Integer id, Integer uid) {
		// 声明返回值
		Integer affectedRows = 0;
		
		// 获取此次需要删除的地址信息
		Address addr 
			= getAddressByIdAndUid(id, uid);
		// 判断查询结果是否为null
		if (addr == null) {
			// 结果为null，抛出DataNotFoundException异常
			throw new DataNotFoundException("尝试删除的数据不存在，请刷新再执行操作！");
		}
		
		// 获取数据成功，即将被删除的数据此时是存在的
		// 则执行删除
		affectedRows = addressMapper
				.delete(id, uid);
		if (affectedRows == 0) {
			// 设置默认地址失败
			throw new DataNotFoundException("删除收货地址失败！收货地址可能已经被删除！请刷新后再次尝试！");
		}
		
		// 判断刚才删除的收货地址是否是默认地址
		if (addr.getIsDefault() == 1) {
			// 是默认地址，则获取剩余的所有数据
			// 将得到List集合
			List<Address> addresses
				= getAddressListByUid(uid);
		
			// 判断集合的长度是否大于0
			if (addresses.size() > 0) {
				// 长度大于0
				// 即删除后还有收货地址的数据
				// 则取出第1条数据的id
				Integer newDefaultId
					= addresses.get(0).getId();
				// 将这条数据设为默认收货地址
				affectedRows 
					= setDefault(uid, newDefaultId);
				// 判断操作是否成功
				if (affectedRows == 0) {
					// 设置默认地址失败
					throw new DataNotFoundException("删除成功！但是，设置默认收货地址失败！新的默认收货地址可能已经被删除！请刷新后再次尝试！");
				}
			} else {
				// 长度为0
				// 即删除后没有收货地址数据了
				// 则不需要执行任何操作
			}
		} else {
			// 不是默认地址，则不需要执行任何操作
		}
		// 返回
		return affectedRows;
	}

	public Integer update(Address address) {
		// 获取省市区的名称
		String recvDistrict
			= getRecvDistrict(address);
		// 在参数中封装省市区的名称
		address.setRecvDistrict(recvDistrict);
		// 执行修改
		return addressMapper.update(address);
	}

	@Transactional
	public Integer setDefault(Integer uid, Integer id) {
		// 先将该用户的所有收货地址设置为非默认
		Integer affectedRows 
			= addressMapper.cancelAllDefault(uid);
		if (affectedRows == 0) {
			throw new DataNotFoundException("设置默认收货地址失败！没有查询到用户的任何收货地址！");
		}
		// 将指定的收货地址设置为默认
		affectedRows 
			= addressMapper.setDefault(uid, id);
		// 判断操作是否成功
		if (affectedRows == 0) {
			// 受影响的行数为0
			// 即设置默认时，这条数据已经不存在
			// 则应该回滚事务
			throw new DataNotFoundException("设置默认失败！数据可能已经被删除！请刷新后再次尝试！");
		} else {
			return affectedRows;
		}
	}
	
	/**
	 * 获取收货人地址的省市区的名称
	 * 
	 * @param address 至少封装了收货人地址中省市区编码的对象
	 * 
	 * @return 省市区的名称
	 */
	private String getRecvDistrict(
			Address address) {
		String provinceCode 
			= address.getRecvProvince(); // "510000"
		String provinceName 
			= districtService.getProvinceNameByCode(provinceCode);
		String cityCode 
			= address.getRecvCity(); // "510100"
		String cityName
			= districtService
				.getCityNameByCode(cityCode);
		String areaCode
			= address.getRecvArea();
		String areaName
			= districtService
				.getAreaNameByCode(areaCode);
		String recvDistrict
			= provinceName + cityName + areaName;
		return recvDistrict;
	}

}
