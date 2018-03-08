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

import io.sunny.modules.sunny.entity.ManageuserEntity;
import io.sunny.modules.sunny.service.ManageuserService;
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
@RequestMapping("manageuser")
public class ManageuserController {
	@Autowired
	private ManageuserService manageuserService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("manageuser:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ManageuserEntity> manageuserList = manageuserService.queryList(query);
		int total = manageuserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(manageuserList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{managerId}")
	@RequiresPermissions("manageuser:info")
	public R info(@PathVariable("managerId") String managerId){
		ManageuserEntity manageuser = manageuserService.queryObject(managerId);
		
		return R.ok().put("manageuser", manageuser);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("manageuser:save")
	public R save(@RequestBody ManageuserEntity manageuser){
		manageuserService.save(manageuser);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("manageuser:update")
	public R update(@RequestBody ManageuserEntity manageuser){
		manageuserService.update(manageuser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("manageuser:delete")
	public R delete(@RequestBody String[] managerIds){
		manageuserService.deleteBatch(managerIds);
		
		return R.ok();
	}
	
}
