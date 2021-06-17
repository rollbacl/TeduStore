package cn.tedu.store.mapper;

import cn.tedu.store.bean.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {

	/**
	 * 增加新收货地址
	 * 
	 * @param address
	 *            收货地址信息
	 * @return 受影响的行数
	 */
	Integer insert(Address address);

	/**
	 * 根据登录用户的uid查询这个用户的所有收货人地址
	 * 
	 * @param uid
	 *            当前登录的用户的uid
	 * @return 这个用户的所有收货人地址
	 */
	List<Address> getAddressListByUid(Integer uid);
	
	/**
	 * 获取指定的收货地址数据
	 * @param id 收货地址数据的id
	 * @param uid 收货地址所归属的用户的id
	 * @return 匹配的数据，如果没有匹配的数据，则返回null
	 */
	Address getAddressByIdAndUid(
            @Param("id") Integer id,
            @Param("uid") Integer uid);

	/**
	 * 删除地址信息
	 *
	 * @param id
	 *            被删除的数据的id
	 * @param uid
	 *            当前登录的用户的id
	 * @return 受影响的行数，可能是0或者1。
	 */
	Integer delete(
            @Param("id") Integer id,
            @Param("uid") Integer uid);

	/**
	 * 修改收货地址
	 *
	 * @param address
	 *            至少包括被修改的数据的ID、所归属用户的uid，和新数据
	 * @return 受影响的行数，即返回0或1。
	 */
	Integer update(Address address);

	/**
	 * 将某用户的所有收址均设置为非默认
	 * @param uid 用户的id
	 * @return 受影响的行数
	 */
	Integer cancelAllDefault(Integer uid);

	/**
	 * 将某用户的某条收货地址设置为默认
	 * @param uid 用户的id
	 * @param id 收货地址的id
	 * @return 受影响的行数
	 */
	Integer setDefault(
            @Param("uid") Integer uid,
            @Param("id") Integer id);

}
