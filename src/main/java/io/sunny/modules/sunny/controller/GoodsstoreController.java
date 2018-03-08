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

import io.sunny.modules.sunny.entity.GoodsstoreEntity;
import io.sunny.modules.sunny.service.GoodsstoreService;
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
@RequestMapping("goodsstore")
public class GoodsstoreController {
	@Autowired
	private GoodsstoreService goodsstoreService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("goodsstore:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GoodsstoreEntity> goodsstoreList = goodsstoreService.queryList(query);
		int total = goodsstoreService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(goodsstoreList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{goodsId}")
	@RequiresPermissions("goodsstore:info")
	public R info(@PathVariable("goodsId") String goodsId){
		GoodsstoreEntity goodsstore = goodsstoreService.queryObject(goodsId);
		
		return R.ok().put("goodsstore", goodsstore);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goodsstore:save")
	public R save(@RequestBody GoodsstoreEntity goodsstore){
		goodsstoreService.save(goodsstore);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goodsstore:update")
	public R update(@RequestBody GoodsstoreEntity goodsstore){
		goodsstoreService.update(goodsstore);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goodsstore:delete")
	public R delete(@RequestBody String[] goodsIds){
		goodsstoreService.deleteBatch(goodsIds);
		
		return R.ok();
	}
	
}
