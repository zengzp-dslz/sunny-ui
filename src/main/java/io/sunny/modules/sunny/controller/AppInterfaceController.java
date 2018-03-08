package io.sunny.modules.sunny.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
public class AppInterfaceController {
	@Autowired
	private SunnystockService sunnystockService;
	
	/**
	 * 保存库存
	 */
	@ResponseBody
	@RequestMapping(value = "/appintf/savestock")
	@RequiresPermissions("sunnystock:save")
	public Map<String, Object> savestock(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException{
		Map<String, Object> result = new HashMap<String, Object>();
		
		SunnystockEntity sunnystock = new SunnystockEntity();
		sunnystock.setMercId(map.get("mercId").toString());
		sunnystock.setBarcode(map.get("barcode").toString());
		sunnystock.setGoodsCid(Long.parseLong(map.get("goodsCid").toString()));
		sunnystock.setGoodsIns(Integer.parseInt(map.get("goodsIns").toString()));
		
		try {
			if(null == sunnystock.getMercId() || "".equals(sunnystock.getMercId())) {
				result.put("code", 10000);
				result.put("message", "未选择便利店");
				return result;
			}
			if(null == sunnystock.getBarcode() || "".equals(sunnystock.getBarcode())) {
				result.put("code", 10001);
				result.put("message", "未填写条形码");
				return result;
			}
			if(null == sunnystock.getGoodsCid()) {
				result.put("code", 10002);
				result.put("message", "未填写成本价");
				return result;
			}
			if(null == sunnystock.getGoodsIns()) {
				result.put("code", 10003);
				result.put("message", "未填写数量");
				return result;
			}
			sunnystockService.save(sunnystock);
			result.put("code", 0);
			result.put("message", "保存成功");
			return result;
		} catch (Exception e) {
			result.put("status", -1);	
			result.put("message", "系统异常");
			return result;
		}
	}
}
