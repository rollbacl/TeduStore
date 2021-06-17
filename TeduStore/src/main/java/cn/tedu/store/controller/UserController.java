package cn.tedu.store.controller;

import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistsException;
import cn.tedu.store.service.ex.UsernameNotFoundException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@RequestMapping("/user")
@Controller
public class UserController
	extends BaseController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	@RequestMapping("/register.do")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping("/login.do")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/password.do")
	public String showPassword() {
		return "user_password";
	}
	
	@RequestMapping("/profile.do")
	public String showProfile(
			ModelMap modelMap, 
			HttpSession session) {
		// 查询当前登录的用户数据
		Integer uid = getUidFromSession(session);
		User user = userService.findUserById(uid);
		
		// 将用户数据转发到前端页面
		modelMap.addAttribute("user", user);
		
		// 执行转发
		return "user_profile";
	}
	
	@RequestMapping("/check_username.do")
	@ResponseBody
	public ResponseResult<Void> checkUsername(
			String username) {
		// 声明返回值
		ResponseResult<Void> rr;
		// 检查用户名是否存在
		boolean result 
			= userService
				.checkUsernameExists(
					username);
		// 判断检查结果
		if (result) {
			// 结果为true，表示用户名已经存在
			rr = new ResponseResult<Void>(
				0, "用户名已经被占用");
		} else {
			// 结果为false，表示用户名尚未被注册
			rr = new ResponseResult<Void>(
				1, "用户名可以使用");
		}
		// 返回
		return rr;
	}
	
	@RequestMapping("/check_email.do")
	@ResponseBody
	public ResponseResult<Void> checkEmail(
			String email) {
		// 声明返回值
		ResponseResult<Void> rr;
		// 验证电子邮箱是否已经被注册
		boolean result = userService
				.checkEmailExists(email);
		// 判断验证结果
		if (result) {
			rr = new ResponseResult<Void>(
				0, "邮箱已经被注册");
		} else {
			rr = new ResponseResult<Void>(
				1, "邮箱可以正常使用");
		}
		// 返回
		return rr;
	}
	
	@RequestMapping("/check_phone.do")
	@ResponseBody
	public ResponseResult<Void> checkPhone(
			String phone) {
		// 声明返回值
		ResponseResult<Void> rr;
		// 验证手机号码是否已经被注册
		boolean result = userService
				.checkPhoneExists(phone);
		// 判断验证结果
		if (result) {
			rr = new ResponseResult<Void>(
					0, "手机号已经被注册");
		} else {
			rr = new ResponseResult<Void>(
					1, "手机号可以正常使用");
		}
		// 返回
		return rr;
	}

	@RequestMapping(value="/handle_register.do",
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleRegister(
		@RequestParam("uname") String username,
		@RequestParam("upwd") String password,
		@RequestParam("phone") String phone,
		@RequestParam("email") String email) {
		// 声明返回值
		ResponseResult<Void> rr;
		
		// 封装数据
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		System.out.println("UserController.handleRegister()");
		System.out.println("\tuser=" + user);
		
		// 执行注册
		try {
			userService.register(user);
			rr = new ResponseResult<Void>(
					1, "注册成功");
			return rr;
		} catch (UsernameAlreadyExistsException e) {
			rr = new ResponseResult<Void>(
					0, e.getMessage());
			return rr;
		}
	}
	
	@RequestMapping(value="/handle_login.do", 
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleLogin(
		@RequestParam("username") String username,
		@RequestParam("password") String password,
		HttpSession session) {
		// 声明返回值
		// 无论登录成功与否，都返回ResponseResult<Void>对象
		ResponseResult<Void> rr;
		
		try {
			// 调用userService完成登录
			User user = 
				userService.login(
					username, password);
			rr = new ResponseResult<Void>(1);
			// 如果登录成功，在返回之前，
			// 调用HttpSession对象的setAttribute(String, Object)方法封装uid及username
			session.setAttribute(
				"uid", user.getId());
			session.setAttribute(
				"username", user.getUsername());
		} catch (UsernameNotFoundException e) {
			// 用户名不存在
			rr = new ResponseResult<Void>(0, e);
		} catch (PasswordNotMatchException e) {
			// 密码错误
			rr = new ResponseResult<Void>(-1, e);
		}
		// 返回
		return rr;
	}
	
	@RequestMapping("/logout.do")
	public String handleLogout(
		HttpServletRequest request,
		HttpSession session) {
		// 清除Session中的所有数据
		session.invalidate();
		// 重向向的目标
		String url = "../main/index.do";
		// 执行重定向
		return "redirect:" + url;
	}

	@RequestMapping(method=RequestMethod.POST, 
		    value="handle_change_password.do")
	@ResponseBody
	public ResponseResult<Void> handleChangePassword(
			@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword,
			HttpSession session) {
		// 声明ResponseResult对象
		ResponseResult<Void> rr;
		// 从Session中获取用户ID
		Integer uid = getUidFromSession(session);
		try {
		    // 调用Service中的changePassword()方法
			userService.changePassword(
				uid, oldPassword, newPassword);
		    // 通过ResponseResult表示修改密码成功
			rr = new ResponseResult<Void>(1, "修改密码成功！");
		} catch (UserNotFoundException e) {
		    // 通过ResponseResult表示修改密码失败：用户不存在
			rr = new ResponseResult<Void>(-1, e);
		} catch (PasswordNotMatchException e) {
		    // 通过ResponseResult表示修改密码失败：密码错误
			rr = new ResponseResult<Void>(-2, e);
		}
		// 返回
		return rr;
	}
	
	@RequestMapping(value="/handle_edit_profile.do",
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleEditProfile(
		String username,
		Integer gender,
		String phone,
		String email,
		HttpSession session) {
		// 声明返回值
		ResponseResult<Void> rr;
		// 调用getUidFromSession()获取uid
		Integer uid = getUidFromSession(session);
		
		try {
			// 调用userService中的editProfile()方法
			userService.editProfile(
				uid, 
				username, gender, 
				phone, email);
			// 更新Session中的username
			User u = userService.findUserById(uid);
			session.setAttribute("username", u.getUsername());
			// 返回操作正确
			rr = new ResponseResult<Void>(1, "修改成功！");
		} catch (UserNotFoundException e) {
			// 返回用户不存在
			rr = new ResponseResult<Void>(-1, e);
		} catch (UsernameAlreadyExistsException e) {
			// 返回用户名已经被占用
			rr = new ResponseResult<Void>(-2, e);
		}
		
		// 返回
		return rr;
	}
	
	//UserController 添加处理图片下载方法
	@RequestMapping(value="/demo.do",
			produces="image/png")
	//produces 用于设定 content-type 属性
	@ResponseBody //与返回值byte[] 配合, 填充
	//Response 的Body部分
	public byte[] pngDemo() throws Exception{
		//读取一个png图片, 返回图片数据即可
		String path=
			"cn/tedu/store/controller/pwd.png";
		//从包中读取文件
		InputStream in =
			getClass().getClassLoader()
			.getResourceAsStream(path);
		//available()方法可以检查流中一次可以
		//读取的字节个数, 小文件就是文件的长度
		byte[] bytes=new byte[in.available()];
		//将文件中全部数据读取到 bytes 数组中
		in.read(bytes);
		in.close();
		return bytes;
	}
	
	@RequestMapping("/check_code.do")
	@ResponseBody
	public ResponseResult<Void> checkCode(
			String code, HttpSession session){
		String c = 
			(String)session.getAttribute("code");
		if(c==null){
			return new ResponseResult<Void>(
					0, "验证失败!");
		}
		//equalsIgnoreCase忽略大小写比较两个字符串
		//是否一致
		if(c.equalsIgnoreCase(code)){
			return new ResponseResult<Void>(
					1, "验证通过!");
		}
		return new ResponseResult<Void>(
				0, "验证失败!");
	}
	
	/**
	 * 生成验证码
	 */
	@RequestMapping(value="/code.do",
			produces="image/png")
	@ResponseBody
	public byte[] code(HttpSession session) throws IOException{
		//利用算法生成一个动态PNG图片
		String code=createCode(4);//生成验证码
		session.setAttribute("code", code);
		System.out.println(code);
		byte[] bytes=createPng(code);//生成图片
		return bytes;
	}
	//生成图片
	private byte[] createPng(String code)
		throws IOException{
		//创建图片对象
		BufferedImage img=new BufferedImage(
			100,40,BufferedImage.TYPE_3BYTE_BGR);
		//img.setRGB(0, 0, 0x0000ff); 
		//img.setRGB(50, 20, 0xffff00);
		//绘制5000个随机色点
		Random r = new Random();
		for(int i=0; i<5000; i++){
			int x = r.nextInt(img.getWidth());
			int y = r.nextInt(img.getHeight());
			int rgb = r.nextInt(0xffffff);//随机色
			img.setRGB(x, y, rgb);
		}
		//利用API绘制验证码字符串
		Graphics2D g = img.createGraphics();
		Color c = new Color(r.nextInt(0xffffff));
		g.setColor(c);
		Font font=new Font(Font.SANS_SERIF,Font.ITALIC, 35);
		g.setFont(font);
		g.drawString(code, 5,34); 
		//利用API绘制5条混淆线
		for(int i=0; i<5; i++){
			int x1=r.nextInt(img.getWidth());
			int y1=r.nextInt(img.getHeight());
			int x2=r.nextInt(img.getWidth());
			int y2=r.nextInt(img.getHeight());
			g.drawLine(x1, y1, x2, y2);
		}
		
		//将图片对象编码为 png 数据
		//创建 数组输出流作为缓存区(酱油瓶)
		ByteArrayOutputStream out=
			new ByteArrayOutputStream();
		//将png图片数据(酱油)保存到缓存区
		ImageIO.write(img, "png", out);
		out.close();
		//获取缓存区中的png数据(酱油)
		byte[] bytes=out.toByteArray();
		return bytes;
	}
	//生成验证码
	private String createCode(int n) {
		String chs="abcdefghijkmnpqxy"
				+ "ABCDEFGHJKLMNPQRSTUV"
				+ "34568";
		char[] code=new char[n];
		Random r = new Random();
		for(int i=0; i<code.length; i++){
			int index=r.nextInt(chs.length());
			code[i]=chs.charAt(index);
		}
		return new String(code);
	}
	
	@RequestMapping(value="/download.do",
			produces="image/png")
	@ResponseBody
	public byte[] downloadPng(
			HttpServletResponse response)
		throws IOException{
		response.setHeader(
			"Content-Disposition",
			"attachment; filename=\"demo.png\"");
		byte[] bytes = createPng("Hello");
		return bytes;
	}
	
	/**
	 * Excel 导出(下载)功能
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/export.do",
		produces="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@ResponseBody
	public byte[] exportExcel(
			HttpServletResponse response)
			throws IOException{
		response.setHeader(
			"Content-Disposition",
			"attachment; filename=\"demo.xlsx\"");
		byte[] bytes=createExcel();
		return bytes;
	}

	private byte[] createExcel()
		throws IOException{
		//利用POI API 创建工作簿(Workbook)
		XSSFWorkbook workbook=new XSSFWorkbook();
		//在工作簿中创建工作表, 参数为工作表名
		XSSFSheet sheet = 
			workbook.createSheet("演示");
		//在工作表中创建行, 参数为行号
		XSSFRow row=sheet.createRow(0);
		//在行中创建 单元格, 参数是单元格的序号
		XSSFCell cell = row.createCell(0);
		//在单元格中添加数据
		cell.setCellValue("Hello World");
		
		ByteArrayOutputStream out=
				new ByteArrayOutputStream();
		//将Excel写到流中
		workbook.write(out); 
		workbook.close();
		out.close();
		//从流中获取全部Excel的数据
		byte[] bytes=out.toByteArray();
		return bytes; 
	}
	
	/**
	 * 上载服务器端处理关键点
	 * 1. 导入上载处理组件 commons-fileupload
	 *    用于解析文件上载请求
	 * 2. 配置Spring MVC上载解析器, 使用 fileupload
	 *    组件
	 *    - 设置最大上载限制
	 *    - 设置文件名的中文编码
	 * 3. 控制器中使用MultipartFile接受上载的文件
	 *    数据. 
	 *    - 注意: 变量名与客户端name属性一致
	 *    - 文件的全部信息可以通过 MultipartFile 
	 *    对象获得  
	 */
	
	@RequestMapping("/upload.do")
	@ResponseBody
	public ResponseResult<Void> upload(
		MultipartFile image,
		String memo, 
		HttpServletRequest request) 
		throws IOException{
		//MultipartFile 封装了全部的上载文件信息
		//利用其方法可以获取全部的上载信息
		//显示上载文件的文件名OriginalFilename
		System.out.println(
			image.getOriginalFilename());
		//文件大小(字节数)image.getSize()
		//返回input name属性的值 image.getName()
		//返回文件的全部字节 image.getBytes()
		//返回流,其中包含文件的全部字节
		//      image.getInputStream();
		//返回文件的类型: image.getContentType()
		//将上载的文件直接保存到一个目标文件中
		//      image.transferTo(file);
		String filename=image.getOriginalFilename();
		//String path = "D:/images";
		String path="/uploads";//WEB路径
		
		//将WEB路径转换为当前操作系统的实际路径
		path = request.getServletContext()
				.getRealPath(path);
		//输出实际路径:
		System.out.println(path); 
		
		File dir=new File(path);
		dir.mkdir();
		File file = new File(dir, filename);
		image.transferTo(file);
		
		System.out.println(memo);
		//返回结果
		return new ResponseResult<Void>(
				1, "Success");
	}
	
	
	@RequestMapping("/upload-images.do")
	@ResponseBody
	public ResponseResult<Void> uploadImages(
			@RequestParam(value="images")
			MultipartFile[] images,
			String memo,
			HttpServletRequest request)
		throws IOException {
		System.out.println(images.length);
		System.out.println(memo);
		//保存文件
		if(images==null){
			System.out.println("没有上载文件");
			return new ResponseResult<Void>(
					0, "没有上载文件");
		}
		String path="/uploads";
		path=request.getServletContext()
				.getRealPath(path);
		File dir=new File(path);
		dir.mkdir();
		for (MultipartFile file : images) {
			String n = file.getOriginalFilename();
			file.transferTo(new File(dir, n));
			System.out.println("Save "+n);
		}
		return new ResponseResult<Void>(
				1, "成功");
	}
}










 