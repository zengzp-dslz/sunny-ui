package io.sunny.modules.sunny.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.sunny.common.utils.PageUtils;
import io.sunny.common.utils.Query;
import io.sunny.common.utils.R;
import io.sunny.modules.sunny.entity.SunnystockEntity;
import io.sunny.modules.sunny.service.SunnystockService;


/**
 * 
 * 
 * @author zdy
 * @email sunny@sunny.com
 * @date 2018-01-16 11:31:55
 */
@CrossOrigin
@RestController
@RequestMapping("sunnystock")
public class SunnystockController {
	@Autowired
	private SunnystockService sunnystockService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sunnystock:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SunnystockEntity> sunnystockList = sunnystockService.queryStock(query);
		int total = sunnystockService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sunnystockList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{stockId}")
	@RequiresPermissions("sunnystock:info")
	public R info(@PathVariable("stockId") Integer stockId){
		SunnystockEntity sunnystock = sunnystockService.queryObject(stockId);
		
		return R.ok().put("sunnystock", sunnystock);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("sunnystock:save")
	public R save(@RequestBody SunnystockEntity sunnystock){
		try {
			if(null == sunnystock.getMercId() || "".equals(sunnystock.getMercId())) {
				return R.error("未选择便利店");
			}
			if(null == sunnystock.getBarcode() || "".equals(sunnystock.getBarcode())) {
				return R.error("未填写条形码");
			}
			if(null == sunnystock.getGoodsCid()) {
				return R.error("未填写成本价");
			}
			if(null == sunnystock.getGoodsIns()) {
				return R.error("未填写数量");
			}
			sunnystockService.save(sunnystock);
			return R.ok();
		} catch (Exception e) {
			return R.error(e.getMessage());
		}
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sunnystock:update")
	public R update(@RequestBody SunnystockEntity sunnystock){
		sunnystockService.update(sunnystock);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sunnystock:delete")
	public R delete(@RequestBody Integer[] stockIds){
		sunnystockService.deleteBatch(stockIds);
		
		return R.ok();
	}
	
}
