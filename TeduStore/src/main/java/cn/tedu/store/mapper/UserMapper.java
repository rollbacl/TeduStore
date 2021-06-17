package cn.tedu.store.mapper;

import cn.tedu.store.bean.User;

public interface UserMapper {

	/**
	 * 增加用户数据
	 * 
	 * @param user
	 *            新的用户数据
	 */
	void insert(User user);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 *            用户名
	 * @return 与用户名匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findUserByUsername(String username);

	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param id
	 *            用户ID
	 * @return 与用户ID匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findUserById(Integer id);

	/**
	 * 获取某电子邮箱对应的数据的数量
	 * 
	 * @param email
	 *            电子邮箱名称
	 * @return 电子邮箱对应的数据的数量
	 */
	Integer getRecordCountByEmail(String email);

	/**
	 * 获取某手机号码对应的数据的数量
	 * 
	 * @param phone
	 *            手机号码
	 * @return 手机号码对应的数据的数量
	 */
	Integer getRecordCountByPhone(String phone);

	/**
	 * 修改用户数据，可用于修改个人资料，也可用于修改密码
	 * 
	 * @params user 必须包含被修改的用户的ID，及需要修改的新数据
	 * @return 受影响的行数，由于是基于ID筛选后的修改，最终表现为0或1。
	 */
	Integer update(User user);

}
