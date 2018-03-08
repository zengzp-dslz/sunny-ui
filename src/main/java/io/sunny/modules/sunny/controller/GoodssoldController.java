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

import io.sunny.modules.sunny.entity.GoodssoldEntity;
import io.sunny.modules.sunny.service.GoodssoldService;
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
@RequestMapping("goodssold")
public class GoodssoldController {
	@Autowired
	private GoodssoldService goodssoldService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("goodssold:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GoodssoldEntity> goodssoldList = goodssoldService.queryList(query);
		int total = goodssoldService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(goodssoldList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{goodsId}")
	@RequiresPermissions("goodssold:info")
	public R info(@PathVariable("goodsId") String goodsId){
		GoodssoldEntity goodssold = goodssoldService.queryObject(goodsId);
		
		return R.ok().put("goodssold", goodssold);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goodssold:save")
	public R save(@RequestBody GoodssoldEntity goodssold){
		goodssoldService.save(goodssold);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goodssold:update")
	public R update(@RequestBody GoodssoldEntity goodssold){
		goodssoldService.update(goodssold);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goodssold:delete")
	public R delete(@RequestBody String[] goodsIds){
		goodssoldService.deleteBatch(goodsIds);
		
		return R.ok();
	}
	
}
