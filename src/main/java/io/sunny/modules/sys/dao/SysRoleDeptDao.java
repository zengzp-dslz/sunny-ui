package io.sunny.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.sys.entity.SysRoleDeptEntity;

import java.util.List;

/**
 * 角色与部门对应关系
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SysRoleDeptDao extends BaseDao<SysRoleDeptEntity> {
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<Long> queryDeptIdList(Long roleId);
}
