package io.sunny.modules.sunny.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.sunny.common.utils.PageUtils;
import io.sunny.common.utils.Query;
import io.sunny.common.utils.R;
import io.sunny.modules.sunny.entity.ProvcitycountryEntity;
import io.sunny.modules.sunny.service.ProvcitycountryService;


/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@CrossOrigin
@RestController
@RequestMapping("provcitycountry")
public class ProvcitycountryController {
	@Autowired
	private ProvcitycountryService provcitycountryService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("provcitycountry:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ProvcitycountryEntity> provcitycountryList = provcitycountryService.queryList(query);
		int total = provcitycountryService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(provcitycountryList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{provCd}")
	@RequiresPermissions("provcitycountry:info")
	public R info(@PathVariable("provCd") String provCd){
		ProvcitycountryEntity provcitycountry = provcitycountryService.queryObject(provCd);
		
		return R.ok().put("provcitycountry", provcitycountry);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("provcitycountry:save")
	public R save(@RequestBody ProvcitycountryEntity provcitycountry){
		provcitycountryService.save(provcitycountry);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("provcitycountry:update")
	public R update(@RequestBody ProvcitycountryEntity provcitycountry){
		provcitycountryService.update(provcitycountry);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("provcitycountry:delete")
	public R delete(@RequestBody String[] provCds){
		provcitycountryService.deleteBatch(provCds);
		
		return R.ok();
	}
	
	/**
	 * 省份下拉框
	 * @throws IOException 
	 */
	@RequestMapping("/selectProv")
	public R selectProv(HttpServletResponse response) throws IOException {
 		List<ProvcitycountryEntity> result = provcitycountryService.selectProv();
		return R.ok().put("provList", result);
	}
	
	/**
	 * 城市下拉框
	 */
	@RequestMapping("/selectCity")
	public R selectCity(String provId) {
		List<ProvcitycountryEntity> result = provcitycountryService.selectCity(provId);
		return R.ok().put("cityList", result);
	}
	
	/**
	 * 地区下拉框
	 */
	@RequestMapping("/selectCountry")
	public R selectCountry(String cityId) {
		List<ProvcitycountryEntity> result = provcitycountryService.selectCountry(cityId);
		return R.ok().put("countryList", result);
	}
}
