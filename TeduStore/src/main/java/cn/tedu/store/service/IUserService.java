package cn.tedu.store.service;

import cn.tedu.store.bean.User;
import cn.tedu.store.service.ex.UsernameAlreadyExistsException;

public interface IUserService {

	/**
	 * 增加用户数据
	 * 
	 * @param user
	 *            新的用户数据
	 * @return 新增加的数据的ID
	 * @throws UsernameAlreadyExistsException
	 *             当注册的用户名已经被占用时抛出该异常
	 */
	Integer register(User user);

	/**
	 * 用户登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 登录成功的用户的数据
	 * @throws UsernameNotFoundException
	 *             用户名不存在
	 * @throws PasswordNotMatchException
	 *             密码不匹配
	 */
	User login(String username, String password);

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
	 * 检查某电子邮箱是否已经在数据表中存在
	 * 
	 * @param email
	 *            电子邮箱
	 * @return 如果存在则返回true，否则返回false
	 */
	boolean checkEmailExists(String email);

	/**
	 * 检查某手机号码是否已经在数据表中存在
	 * 
	 * @param phone
	 *            手机号码
	 * @return 如果存在则返回true，否则返回false
	 */
	boolean checkPhoneExists(String phone);

	/**
	 * 检查用户名在数据表中是否存在
	 * 
	 * @param username
	 *            用户名
	 * @return 如果存在匹配的数据，则返回true，否则，返回false
	 */
	boolean checkUsernameExists(String username);

	/**
	 * 修改密码
	 * 
	 * @param uid
	 *            被修改的用户的id
	 * @param oldPassword
	 *            原密码
	 * @param newPassword
	 *            新密码
	 * @return 受影响的行数
	 * @throws UserNotFoundException
	 *             用户信息不存在
	 * @throws PasswordNotMatchException
	 *             原密码不正确
	 */
	Integer changePassword(
            Integer uid,
            String oldPassword,
            String newPassword);

	/**
	 * 修改用户基本资料
	 * @param uid 被修改的用户的id
	 * @param username 新用户名
	 * @param gender 新性别
	 * @param phone 新手机号码
	 * @param email 新电子邮箱
	 * @return 受影响的行数
	 * @throws UserNotFoundException
	 *             用户信息不存在
	 * @throws UsernameAlreadyExistsException
	 *             用户名已经被占用
	 */
	Integer editProfile(
            Integer uid,
            String username,
            Integer gender,
            String phone,
            String email);

}
