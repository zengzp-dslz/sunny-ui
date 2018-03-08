package io.sunny.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Controller
public class SysPageController {

	@RequestMapping("modules/{module}/{url}.html")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		return "modules/" + module + "/" + url;
	}

	@RequestMapping("{url}.html")
	public String url(@PathVariable("url") String url){
		return url;
	}

	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
