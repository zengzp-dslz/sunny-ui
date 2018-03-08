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

import io.sunny.modules.sunny.entity.SunnyorderinfoEntity;
import io.sunny.modules.sunny.service.SunnyorderinfoService;
import io.sunny.common.utils.DateTimeUtil;
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
@RequestMapping("sunnyorderinfo")
public class SunnyorderinfoController {
	@Autowired
	private SunnyorderinfoService sunnyorderinfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sunnyorderinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		if(null == params.get("ordNo") || "".equals(params.get("ordNo")) || null == params.get("mercId") || "".equals(params.get("mercId"))) {
			if(null == params.get("starDate") || "".equals(params.get("starDate"))) {
				params.put("starDate",DateTimeUtil.currentDate() + "000000");
			}
			if(null == params.get("endDate") || "".equals(params.get("endDate"))) {
				params.put("endDate",DateTimeUtil.currentDate() + "235959");
			}
		}
		
		//查询列表数据
        Query query = new Query(params);

		List<SunnyorderinfoEntity> sunnyorderinfoList = sunnyorderinfoService.queryList(query);
		int total = sunnyorderinfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sunnyorderinfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{ordNo}")
	@RequiresPermissions("sunnyorderinfo:info")
	public R info(@PathVariable("ordNo") String ordNo){
		SunnyorderinfoEntity sunnyorderinfo = sunnyorderinfoService.queryObject(ordNo);
		
		return R.ok().put("sunnyorderinfo", sunnyorderinfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sunnyorderinfo:save")
	public R save(@RequestBody SunnyorderinfoEntity sunnyorderinfo){
		sunnyorderinfoService.save(sunnyorderinfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sunnyorderinfo:update")
	public R update(@RequestBody SunnyorderinfoEntity sunnyorderinfo){
		sunnyorderinfoService.update(sunnyorderinfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sunnyorderinfo:delete")
	public R delete(@RequestBody String[] ordNos){
		sunnyorderinfoService.deleteBatch(ordNos);
		
		return R.ok();
	}
	
}
