package cn.tedu.store.service;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistsException;
import cn.tedu.store.service.ex.UsernameNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	@Value("#{dbConfig.salt}")
	private String salt;

	public Integer register(User user) {
		// 判断尝试注册的用户信息中的用户名是否被占用
		if (checkUsernameExists(
				user.getUsername())) {
			// 查询结果为true，
			// 即新数据中的用户名已经被占用，
			// 则不允许注册，抛出业务异常
			throw new UsernameAlreadyExistsException("用户名已经被占用");
		} else {
			// 查询结果为false，
			// 即用户名尚未被占用，
			// 则执行注册
			
			// 对用户密码进行加密处理
			// 获取密码明文:
			String pwd=user.getPassword();
			
			// 加盐摘要,计算密码的密文
			String md5=
				DigestUtils.md5Hex(pwd+salt);
			// 保存密文到数据库(密码明文被丢弃!)
			user.setPassword(md5); 	
			//打桩输出: 
			System.out.println(salt);
			System.out.println(user.getPassword());
			
			userMapper.insert(user);
			// 返回新数据的id
			return user.getId();
		}
	}
	
	public User login(String username, String password) {
		// 根据用户名获取用户数据
		User u = findUserByUsername(username);
		// 判断是否获取到用户数据
		if (u != null) {
			// 数据不为null，即获取到了数据
			// 即用户名存在
			// 则判断密码
			
			// 计算 明文 password 的加盐摘要,然后比较摘要
			String md5=DigestUtils.md5Hex(
					password + salt);
			//打桩
			System.out.println(u.getPassword());
			System.out.println(md5);
			
			if (u.getPassword().equals(md5)) {
				// 密码匹配，则登录成功
				return u;
			} else {
				// 密码不匹配，则登录失败
				// 则抛出业务异常
				throw new PasswordNotMatchException("密码错误");
			}
		} else {
			// 数据为null，即根据用户名查询不到有效数据
			// 即用户名不存在
			// 则抛出业务异常
			throw new UsernameNotFoundException("用户名不存在");
		}
	}

	public Integer changePassword(
			Integer uid, 
			String oldPassword, 
			String newPassword) {
		// 根据uid查询用户数据
		User user = findUserById(uid);
	    // 判断查询到的数据是否为null
		if (user == null) {
			// 查询到的数据为null，
			// 即用户数据并不存在，
			// 则抛出UserNotFoundException
			throw new UserNotFoundException(
				"登录已过期，或用户数据不存在，请重新登录后再次尝试！");
		} else {
		    // 查询到匹配的数据，
			// 即用户数据存在，
			// 则判断密码是否匹配
			
			// 比较加密的密码
			String md5=DigestUtils.md5Hex(
				oldPassword + salt);
			
			if (user.getPassword().equals(md5)) {
			    // 原密码匹配，
				// 则创建User对象，
				User newUser = new User();
				// 将uid和newPassword封装
				newUser.setId(uid);
				
				//加密密码
				md5 = DigestUtils.md5Hex(
					newPassword+salt);
				
				newUser.setPassword(md5);
				
			    // 执行修改：update(User user)
				return userMapper.update(newUser);
			} else {
				// 原密码不匹配，
				// 则抛出PasswordNotMatchException
				throw new PasswordNotMatchException("原密码不正确！");
			}
		}
	}

	public Integer editProfile(
			Integer uid, 
			String username, 
			Integer gender, 
			String phone, 
			String email) {
		// 1. 创建User对象，用于调用持久层方法
		User user = new User();
		
		// 2.1. 封装用户名
		// 根据uid查询用户数据
		User u = findUserById(uid);
		// 判断用户数据是否存在
		if (u == null) {
			throw new UserNotFoundException(
				"登录已过期，或用户数据不存在，请重新登录后再次尝试！");
		}
		// 用户数据存在，则判断新用户名与现有用户名是否一致
		if (u.getUsername().equals(username)) {
			// 一致，新用户名就是现有用户名，无须修改
			// 无须为user中的username赋值
		} else {
			// 提交的是新用户名，需要判断新用户名是否被占用
			User u2 = findUserByUsername(username);
			// 判断是否有匹配的数据
			if (u2 == null) {
				// 新用户名没有匹配的数据，即没有被占用
				// 可以修改用户名
				user.setUsername(username);
			} else {
				// 找到新用户名存在的数据，即用户名被占用
				throw new UsernameAlreadyExistsException("用户名已经被占用！");
			}
		}
		
		// 2.2. 封装性别
		user.setGender(gender);
		
		// 2.3. 封装手机号码
		if (phone != null 
			&& phone.length() >= 11) {
			user.setPhone(phone);
		}
		
		// 2.4. 封装电子邮箱
		if (email != null 
			&& !"".equals(email)) {
			user.setEmail(email);
		}
		
		// 2.5. 封装uid
		user.setId(uid);
		
		// 2.6. 封装修改日志数据
		user.setModifiedUser("[System]");
		user.setModifiedTime(new Date());
		
		// 3. 执行修改数据
		return userMapper.update(user);
	}

	public User findUserByUsername(String username) {
		return userMapper
				.findUserByUsername(username);
	}

	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}


	public boolean checkUsernameExists(String username) {
		return findUserByUsername(username) != null;
	}
	
	public boolean checkEmailExists(String email) {
		return userMapper
				.getRecordCountByEmail(email) > 0;
	}

	public boolean checkPhoneExists(String phone) {
		return userMapper
				.getRecordCountByPhone(phone) > 0;
	}
	


}








