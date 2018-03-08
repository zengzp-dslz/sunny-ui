package io.sunny.modules.gen.service;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface SysGeneratorService {
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
	
	/**
	 * 生成代码
	 */
	byte[] generatorCode(String[] tableNames);
}
