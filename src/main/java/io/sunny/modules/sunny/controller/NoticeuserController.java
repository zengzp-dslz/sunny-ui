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

import io.sunny.modules.sunny.entity.NoticeuserEntity;
import io.sunny.modules.sunny.service.NoticeuserService;
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
@RequestMapping("noticeuser")
public class NoticeuserController {
	@Autowired
	private NoticeuserService noticeuserService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("noticeuser:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<NoticeuserEntity> noticeuserList = noticeuserService.queryList(query);
		int total = noticeuserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(noticeuserList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{nationCode}")
	@RequiresPermissions("noticeuser:info")
	public R info(@PathVariable("nationCode") String nationCode){
		NoticeuserEntity noticeuser = noticeuserService.queryObject(nationCode);
		
		return R.ok().put("noticeuser", noticeuser);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("noticeuser:save")
	public R save(@RequestBody NoticeuserEntity noticeuser){
		noticeuserService.save(noticeuser);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("noticeuser:update")
	public R update(@RequestBody NoticeuserEntity noticeuser){
		noticeuserService.update(noticeuser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("noticeuser:delete")
	public R delete(@RequestBody String[] nationCodes){
		noticeuserService.deleteBatch(nationCodes);
		
		return R.ok();
	}
	
}
