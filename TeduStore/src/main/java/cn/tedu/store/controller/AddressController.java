package cn.tedu.store.controller;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ex.DataNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {

	@Resource
	private IAddressService addressService;

	@RequestMapping("/list.do")
	public String showList() {
		return "address_list";
	}

	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleAdd(Address address, HttpSession session) {
		// 测试
		System.out.println("AddressController.handleAdd()");
		System.out.println("\taddress : " + address);

		// 获取uid
		Integer uid = getUidFromSession(session);
		// 将uid封装到address中
		address.setUid(uid);
		// 执行增加
		addressService.add(address);
		// 创建返回值
		ResponseResult<Void> rr = new ResponseResult<Void>(1, "增加新收货地址成功！");
		// 返回
		return rr;
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public ResponseResult<Void> handelDelete(Integer id, HttpSession session) {
		// 声明返回值
		ResponseResult<Void> rr;

		// 获取uid
		Integer uid = getUidFromSession(session);

		// 通过addressService执行删除
		try {
			addressService.delete(id, uid);
			rr = new ResponseResult<Void>(1, "删除成功！");
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
		}

		// 返回
		return rr;
	}

	@RequestMapping(value = "/handle_update.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleUpdate(Address address, HttpSession session) {
		// 声明返回值
		ResponseResult<Void> rr;

		// 获取uid并封装到address中
		Integer uid = getUidFromSession(session);
		address.setUid(uid);

		// 执行修改
		Integer affectedRows = addressService.update(address);

		// 根据执行结果创建返回值对象
		if (affectedRows == 1) {
			rr = new ResponseResult<Void>(1, "修改成功！");
		} else {
			rr = new ResponseResult<Void>(0, "修改失败！数据可能已经被删除，或者当前登录信息有误！");
		}

		// 返回
		return rr;
	}

	@RequestMapping("/set_default.do")
	@ResponseBody
	public ResponseResult<Void> handleSetDefault(Integer id, HttpSession session) {
		// 声明返回值
		ResponseResult<Void> rr;

		// 获取uid
		Integer uid = getUidFromSession(session);

		// 执行
		try {
			addressService.setDefault(uid, id);
			rr = new ResponseResult<Void>(1, "设置默认地址成功！");
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
		}

		// 返回
		return rr;
	}

	@RequestMapping("/get_list.do")
	@ResponseBody
	public ResponseResult<List<Address>> getAddressListByUid(HttpSession session) {
		// 声明返回值
		ResponseResult<List<Address>> rr;

		// 通过addressService获取数据
		Integer uid = getUidFromSession(session);
		List<Address> data = addressService.getAddressListByUid(uid);

		// 创建返回值对象
		rr = new ResponseResult<List<Address>>(1, data);

		// 返回
		return rr;
	}

	@RequestMapping("/get.do")
	@ResponseBody
	public ResponseResult<Address> getAddressByIdAndUid(Integer id, HttpSession session) {
		// 声明返回值
		ResponseResult<Address> rr;

		// 通过Session获取uid
		Integer uid = getUidFromSession(session);

		// 通过addressService获取数据
		Address data = addressService.getAddressByIdAndUid(id, uid);

		// 创建返回值对象
		rr = new ResponseResult<Address>(1, data);

		// 返回
		return rr;
	}

}
