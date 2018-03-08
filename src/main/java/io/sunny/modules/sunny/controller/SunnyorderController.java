package io.sunny.modules.sunny.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.sunny.modules.sunny.entity.SunnyorderEntity;
import io.sunny.modules.sunny.entity.SunnysaleEntity;
import io.sunny.modules.sunny.service.SunnyorderService;
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
@CrossOrigin
@RequestMapping("sunnyorder")
public class SunnyorderController {
	@Autowired
	private SunnyorderService sunnyorderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sunnyorder:list")
	public R list(@RequestParam Map<String, Object> params){
		if(null == params.get("starDate") || "".equals(params.get("starDate"))) {
			params.put("starDate",DateTimeUtil.currentDate());
		}
		if(null == params.get("endDate") || "".equals(params.get("endDate"))) {
			params.put("endDate",DateTimeUtil.currentDate());
		}
		//查询列表数据
        Query query = new Query(params);

		List<SunnyorderEntity> sunnyorderList = sunnyorderService.queryList(query);
		int total = sunnyorderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sunnyorderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{ordNo}")
	@RequiresPermissions("sunnyorder:info")
	public R info(@PathVariable("ordNo") String ordNo){
		SunnyorderEntity sunnyorder = sunnyorderService.queryObject(ordNo);
		
		return R.ok().put("sunnyorder", sunnyorder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sunnyorder:save")
	public R save(@RequestBody SunnyorderEntity sunnyorder){
		sunnyorderService.save(sunnyorder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sunnyorder:update")
	public R update(@RequestBody SunnyorderEntity sunnyorder){
		sunnyorderService.update(sunnyorder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sunnyorder:delete")
	public R delete(@RequestBody String[] ordNos){
		sunnyorderService.deleteBatch(ordNos);
		
		return R.ok();
	}
	
	/**
	 * 日销售统计
	 */
	@RequestMapping("/daysale")
	@RequiresPermissions("sunnyorder:daysale")
	public R daysale(@RequestParam Map<String, Object> map){
		//查询列表数据
        Query query = new Query(map);
		List<SunnysaleEntity> sunnysaleList = sunnyorderService.queryDaySale(query);
		int total = sunnyorderService.querydaysaleTotal(map);
		
		PageUtils pageUtil = new PageUtils(sunnysaleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 月销售统计
	 */
	@RequestMapping("/monthsale")
	@RequiresPermissions("sunnyorder:monthsale")
	public R monthsale(@RequestParam Map<String, Object> map){
		//查询列表数据
        Query query = new Query(map);
		List<SunnysaleEntity> sunnysaleList = sunnyorderService.queryMonthSale(map);
		int total = sunnyorderService.querymonthsaleTotal(map);
		
		PageUtils pageUtil = new PageUtils(sunnysaleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
}
