package io.sunny.modules.sunny.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.sunny.modules.sunny.entity.SunnyuserEntity;
import io.sunny.modules.sunny.service.SunnyuserService;
import io.sunny.common.utils.PageUtils;
import io.sunny.common.utils.Query;
import io.sunny.common.utils.R;


/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@RestController
@RequestMapping("sunnyuser")
public class SunnyuserController {
	@Autowired
	private SunnyuserService sunnyuserService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sunnyuser:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SunnyuserEntity> sunnyuserList = sunnyuserService.queryList(query);
		int total = sunnyuserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sunnyuserList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{userType}")
	@RequiresPermissions("sunnyuser:info")
	public R info(@PathVariable("userType") String userType){
		SunnyuserEntity sunnyuser = sunnyuserService.queryObject(userType);
		
		return R.ok().put("sunnyuser", sunnyuser);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sunnyuser:save")
	public R save(@RequestBody SunnyuserEntity sunnyuser){
		sunnyuserService.save(sunnyuser);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sunnyuser:update")
	public R update(@RequestBody SunnyuserEntity sunnyuser){
		sunnyuserService.update(sunnyuser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sunnyuser:delete")
	public R delete(@RequestBody String[] userTypes){
		sunnyuserService.deleteBatch(userTypes);
		
		return R.ok();
	}
	
}
