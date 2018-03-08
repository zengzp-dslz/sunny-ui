package io.sunny.modules.sunny.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.sunny.common.utils.DateTimeUtil;
import io.sunny.common.utils.R;
import io.sunny.modules.sunny.entity.SunnymercEntity;
import io.sunny.modules.sunny.entity.SunnyorderEntity;
import io.sunny.modules.sunny.entity.SunnyorderinfoEntity;
import io.sunny.modules.sunny.entity.SunnystockEntity;
import io.sunny.modules.sunny.service.SunnymercService;
import io.sunny.modules.sunny.service.SunnyorderService;
import io.sunny.modules.sunny.service.SunnyorderinfoService;
import io.sunny.modules.sunny.service.SunnystockService;
import io.sunny.modules.sys.shiro.ShiroUtils;


/**
 * 
 * 
 * @author zdy
 * @email sunny@sunny.com
 * @date 2018-01-16 11:31:55
 */
@RestController
public class InterfaceController {
	@Autowired
	private SunnystockService sunnystockService;
	
	@Autowired
	private SunnymercService sunnymercService;
	
	@Autowired
	private SunnyorderService sunnyorderService;
	
	@Autowired
	private SunnyorderinfoService sunnyorderinfoService;
	
	/**
	 * 保存库存信息
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/intf/savestock")
	public Map<String, Object> saveUser(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			sunnystockService.savestock(map);
			result.put("status", 1);
			result.put("message", "success");
		} catch (Exception e) {
			result.put("status", 0);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 登录接口
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/intf/login")
	public Map<String, Object> login(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Subject subject = ShiroUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(map.get("username").toString(), map.get("password").toString());
			subject.login(token);
			result.put("status", 1);
			result.put("message", "success");
		}catch (UnknownAccountException e) {
			result.put("status", 0);
			result.put("message", "系统异常");
		}catch (IncorrectCredentialsException e) {
			result.put("status", 0);
			result.put("message", "账号或密码不正确");
		}catch (LockedAccountException e) {
			result.put("status", 0);
			result.put("message", "账号已被锁定,请联系管理员");
		}catch (AuthenticationException e) {
			result.put("status", 0);
			result.put("message", "账户验证失败");
		}
		return result;
	}
	
	
	/**
	 * 查询及时库存
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/intf/findstock")
	public Map<String, Object> findstock(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int count = sunnystockService.queryPageCount(map);
			List<SunnystockEntity> list = sunnystockService.queryPageList(map);
			result.put("status", 1);
			result.put("message", "success");
			result.put("pageCount", count);
			result.put("pageList", list);
		} catch (Exception e) {
			result.put("status", 0);
			result.put("message", "数据异常");
		}
		return result;
	}
	
	/**
	 * 查询商户列表
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/intf/findmerc")
	public Map<String, Object> findmerc(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int count = sunnymercService.queryPageCount();
			List<SunnymercEntity> list = sunnymercService.queryPageList(map);
			result.put("status", 1);
			result.put("message", "success");
			result.put("pageCount", count);
			result.put("pageList", list);
		} catch (Exception e) {
			result.put("status", 0);
			result.put("message", "数据异常");
		}
		return result;
	}
	
	/**
	 * 查询商铺订单
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/intf/findmercorder")
	public Map<String, Object> findmercorder(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(null == map.get("starDate") || "".equals(map.get("starDate"))) {
				map.put("starDate",DateTimeUtil.currentDate());
			}
			if(null == map.get("endDate") || "".equals(map.get("endDate"))) {
				map.put("endDate",DateTimeUtil.currentDate());
			}
			int count = sunnyorderService.queryPageCount(map);
			List<SunnyorderEntity> list = sunnyorderService.queryPageList(map);
			result.put("status", 1);
			result.put("message", "success");
			result.put("pageCount", count);
			result.put("pageList", list);
		} catch (Exception e) {
			result.put("status", 0);
			result.put("message", "数据异常");
		}
		return result;
	}
	
	/**
	 * 查询商铺订单
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/intf/findmercorderinfo")
	public Map<String, Object> findmercorderinfo(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int count = sunnyorderinfoService.queryPageCount(map);
			List<SunnyorderinfoEntity> list = sunnyorderinfoService.queryPageList(map);
			result.put("status", 1);
			result.put("message", "success");
			result.put("pageCount", count);
			result.put("pageList", list);
		} catch (Exception e) {
			result.put("status", 0);
			result.put("message", "数据异常");
		}
		return result;
	}
}
