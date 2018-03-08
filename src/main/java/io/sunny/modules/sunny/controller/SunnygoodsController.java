package io.sunny.modules.sunny.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.sunny.modules.sunny.entity.SunnygoodsEntity;
import io.sunny.modules.sunny.service.SunnygoodsService;
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
@CrossOrigin
@RestController
@RequestMapping("sunnygoods")
public class SunnygoodsController {
	@Autowired
	private SunnygoodsService sunnygoodsService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sunnygoods:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		List<SunnygoodsEntity> sunnygoodsList = sunnygoodsService.queryList(query);
		int total = sunnygoodsService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(sunnygoodsList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{goodsId}")
	@RequiresPermissions("sunnygoods:info")
	public R info(@PathVariable("goodsId") String goodsId) {
		SunnygoodsEntity sunnygoods = sunnygoodsService.queryObject(goodsId);

		return R.ok().put("sunnygoods", sunnygoods);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sunnygoods:save")
	public R save(@RequestBody SunnygoodsEntity sunnygoods) {
		if (sunnygoods != null) {
			sunnygoodsService.save(sunnygoods);
			return R.ok();
		} else {
			return R.error("sunnygoods is null");
		}

	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sunnygoods:update")
	public R update(@RequestBody SunnygoodsEntity sunnygoods) {
		if (sunnygoods != null && StringUtils.isNotBlank(sunnygoods.getGoodsId())) {
			sunnygoodsService.update(sunnygoods);
			return R.ok();
		} else {
			return R.error("sunnygoods is null or sunnygoods.goodsId is null");
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sunnygoods:delete")
	public R delete(@RequestBody String[] goodsIds) {
		sunnygoodsService.deleteBatch(goodsIds);

		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/queryBarcode")
	public R queryBarcode(String barcode) {
		if(null == barcode || "".equals(barcode)) {
			return R.error("未填写条形码");
		}
		SunnygoodsEntity sunnygoodsEntity = sunnygoodsService.queryBarcode(barcode);
		if(null != sunnygoodsEntity) {
			return R.ok().put("goodsEntity", sunnygoodsEntity);
		} else {
			return R.error("条形码" + barcode + "商品不存在");
		}
		
	}

}
