package io.sunny.modules.sunny.controller;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import io.sunny.common.utils.DateTimeUtil;
import io.sunny.common.utils.MethodAES;
import io.sunny.common.utils.PageUtils;
import io.sunny.common.utils.Query;
import io.sunny.common.utils.R;
import io.sunny.common.utils.http.HttpParam;
import io.sunny.common.utils.http.HttpUtil;
import io.sunny.modules.sunny.entity.SunnymercEntity;
import io.sunny.modules.sunny.service.SunnymercService;
import io.sunny.modules.sunny.service.WxAcodeService;
import net.sf.json.JSONObject;


/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@RestController
@CrossOrigin
@RequestMapping("sunnymerc")
public class SunnymercController {
	@Autowired
	private SunnymercService sunnymercService;
	@Autowired
	private WxAcodeService wxAcodeService;
	@Value("${interfaceUrl.mercurl}")
	private String mercUrl;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sunnymerc:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SunnymercEntity> sunnymercList = sunnymercService.queryList(query);
		int total = sunnymercService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sunnymercList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 下拉框
	 */
	@RequestMapping("/select")
	public R select() {
		List<SunnymercEntity> selectList = sunnymercService.queryList(null);
		
		SunnymercEntity sunnymercEntity = new SunnymercEntity();
		sunnymercEntity.setId("0");
		sunnymercEntity.setName("全部");
		selectList.add(sunnymercEntity);
		
		return R.ok().put("mercList", selectList);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{mercId}")
	@RequiresPermissions("sunnymerc:info")
	public R info(@PathVariable("mercId") String mercId){
		SunnymercEntity sunnymerc = sunnymercService.queryObject(mercId);
		
		return R.ok().put("sunnymerc", sunnymerc);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sunnymerc:save")
	public R save(@RequestBody SunnymercEntity sunnymerc){
		int count = sunnymercService.queryTotal(null);
		sunnymerc.setMercId("8880" + sunnymerc.getMercCountry() + "0" + String.format("%0" + 5 + "d", count + 1));
		sunnymerc.setTmSmp(DateTimeUtil.currentDateTime());

		String jsonStr = "{\"mercId\":\"" + sunnymerc.getMercId() + " \",\"mercNm\":\"" + sunnymerc.getMercNm() + "\",\"mercAddr\":\"" +  sunnymerc.getMercAddr() + "\"}";
		String aesStr = MethodAES.outsideEncrypt(jsonStr);

		JSONObject json = new JSONObject();
		json.put("data", aesStr);
		
		String urlStr = mercUrl + "save";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();  
        String httpStr = null;  
        HttpParam param = new HttpParam();
        param.setUrl(urlStr);
        param.setKeyValueData(json);
        CloseableHttpResponse chresponse = null;
        try {
        	HttpPost httpPost = HttpUtil.handleHttpPost(param);
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题  
            stringEntity.setContentEncoding("UTF-8");  
            stringEntity.setContentType("application/json");  
            httpPost.setEntity(stringEntity);  
            chresponse = httpClient.execute(httpPost);  
            HttpEntity entity = chresponse.getEntity();  
            httpStr = EntityUtils.toString(entity, "UTF-8");  
		} catch (Exception e) {
			e.printStackTrace();
		}
        Map<String, Object> result = JSONObject.fromObject(httpStr);
		if(result.get("status").equals("1")) {
			sunnymercService.save(sunnymerc);
			wxAcodeService.createWxAcode(sunnymerc.getMercId(), "in");
			wxAcodeService.createWxAcode(sunnymerc.getMercId(), "out");
			return R.ok();
		} else {
			return R.error();
		}
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sunnymerc:update")
	public R update(@RequestBody SunnymercEntity sunnymerc){
		String jsonStr = "{\"mercId\":\"" + sunnymerc.getMercId() + " \",\"mercNm\":\"" + sunnymerc.getMercNm() + "\",\"mercAddr\":\"" +  sunnymerc.getMercAddr() + "\"}";
		String aesStr = MethodAES.outsideEncrypt(jsonStr);
		
		JSONObject json = new JSONObject();
		json.put("data", aesStr);
		
		String urlStr = mercUrl + "edit";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();  
        String httpStr = null;  
        HttpParam param = new HttpParam();
        param.setUrl(urlStr);
        param.setKeyValueData(json);
        CloseableHttpResponse chresponse = null;
        
        try {
        	HttpPost httpPost = HttpUtil.handleHttpPost(param);
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题  
            stringEntity.setContentEncoding("UTF-8");  
            stringEntity.setContentType("application/json");  
            httpPost.setEntity(stringEntity);  
            chresponse = httpClient.execute(httpPost);  
            HttpEntity entity = chresponse.getEntity();  
            httpStr = EntityUtils.toString(entity, "UTF-8");  
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = JSONObject.fromObject(httpStr);
		if(result.get("status").equals("1")) {
			sunnymercService.update(sunnymerc);
			return R.ok();
		} else {
			return R.error();
		}
		
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sunnymerc:delete")
	public R delete(@RequestBody String[] mercIds){
		for (int i = 0; i < mercIds.length; i++) {
			String jsonStr = "{\"mercId\":\"" + mercIds[i] + " \"}";
			String aesStr = MethodAES.outsideEncrypt(jsonStr);
			
			JSONObject json = new JSONObject();
			json.put("data", aesStr);
			
			String urlStr = mercUrl + "del";
			
			CloseableHttpClient httpClient = HttpClients.createDefault();  
	        String httpStr = null;  
	        HttpParam param = new HttpParam();
	        param.setUrl(urlStr);
	        param.setKeyValueData(json);
	        CloseableHttpResponse chresponse = null;
	        try {
	        	HttpPost httpPost = HttpUtil.handleHttpPost(param);
	            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题
	            stringEntity.setContentEncoding("UTF-8");
	            stringEntity.setContentType("application/json");
	            httpPost.setEntity(stringEntity);
	            chresponse = httpClient.execute(httpPost);
	            HttpEntity entity = chresponse.getEntity();
	            httpStr = EntityUtils.toString(entity, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		sunnymercService.deleteBatch(mercIds);
		
		return R.ok();
	}
	
}
