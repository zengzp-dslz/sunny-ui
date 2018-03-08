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

import io.sunny.modules.sunny.entity.SunnystocklogEntity;
import io.sunny.modules.sunny.service.SunnystocklogService;
import io.sunny.common.utils.PageUtils;
import io.sunny.common.utils.Query;
import io.sunny.common.utils.R;


/**
 * 
 * 
 * @author zdy
 * @email sunny@sunny.com
 * @date 2018-01-16 11:31:55
 */
@RestController
@RequestMapping("sunnystocklog")
public class SunnystocklogController {
	@Autowired
	private SunnystocklogService sunnystocklogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sunnystocklog:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SunnystocklogEntity> sunnystocklogList = sunnystocklogService.queryList(query);
		int total = sunnystocklogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sunnystocklogList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{stocklogId}")
	@RequiresPermissions("sunnystocklog:info")
	public R info(@PathVariable("stocklogId") Integer stocklogId){
		SunnystocklogEntity sunnystocklog = sunnystocklogService.queryObject(stocklogId);
		
		return R.ok().put("sunnystocklog", sunnystocklog);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sunnystocklog:save")
	public R save(@RequestBody SunnystocklogEntity sunnystocklog){
		sunnystocklogService.save(sunnystocklog);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sunnystocklog:update")
	public R update(@RequestBody SunnystocklogEntity sunnystocklog){
		sunnystocklogService.update(sunnystocklog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sunnystocklog:delete")
	public R delete(@RequestBody Integer[] stocklogIds){
		sunnystocklogService.deleteBatch(stocklogIds);
		
		return R.ok();
	}
	
}
