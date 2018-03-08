package io.sunny.modules.gen.service.impl;


import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.gen.dao.SysGeneratorDao;
import io.sunny.modules.gen.service.SysGeneratorService;
import io.sunny.modules.gen.utils.GenUtils;

@Service("sysGeneratorService")
public class SysGeneratorServiceImpl implements SysGeneratorService {
	@Autowired
	private SysGeneratorDao sysGeneratorDao;

	@Override
	@TargetDataSource(name = "sunny")
	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		return sysGeneratorDao.queryList(map);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public int queryTotal(Map<String, Object> map) {
		return sysGeneratorDao.queryTotal(map);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public Map<String, String> queryTable(String tableName) {
		return sysGeneratorDao.queryTable(tableName);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<Map<String, String>> queryColumns(String tableName) {
		return sysGeneratorDao.queryColumns(tableName);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		
		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
