package cn.tedu.store.service;

import cn.tedu.store.bean.Address;

import java.util.List;

public interface IAddressService {

	/**
	 * 增加新收货地址
	 * 
	 * @param address
	 *            新收货地址
	 * @return 新增加的收货地址的id
	 */
	Integer add(Address address);

	/**
	 * 根据登录用户的uid查询这个用户的所有收货人地址
	 * 
	 * @param uid
	 *            当前登录的用户的uid
	 * @return 这个用户的所有收货人地址，如果没有数据，则返回长度为0的List集合，不会返回null值。
	 */
	List<Address> getAddressListByUid(
            Integer uid);

	/**
	 * 获取指定的收货地址数据
	 *
	 * @param id
	 *            收货地址数据的id
	 * @param uid
	 *            收货地址所归属的用户的id
	 * @return 匹配的数据，如果没有匹配的数据，则返回null
	 */
	Address getAddressByIdAndUid(
            Integer id,
            Integer uid);

	/**
	 * 删除地址信息
	 * 
	 * @param id
	 *            被删除的数据的id
	 * @param uid
	 *            当前登录的用户的id
	 * @return 受影响的行数，可能是0或者1。
	 * @throws DataNotFoundException 当数据不存在时抛出此异常
	 */
	Integer delete(Integer id, Integer uid);

	/**
	 * 修改收货地址
	 * 
	 * @param address
	 *            至少包括被修改的数据的ID、所归属用户的uid，和新数据
	 * @return 受影响的行数，即返回0或1。
	 */
	Integer update(Address address);
	
	/**
	 * 将某用户的某条收货地址设置为默认
	 * @param uid 用户的id
	 * @param id 收货地址的id
	 * @return 如果设置成功，将返回1，否则，将返回0，失败的原因可能因为数据已经被删除，或uid过期。
	 */
	Integer setDefault(Integer uid, Integer id);

}
