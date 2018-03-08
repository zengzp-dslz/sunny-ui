package io.sunny.modules.sys.service;

import java.util.List;


/**
 * 角色与部门对应关系
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface SysRoleDeptService {
	
	void saveOrUpdate(Long roleId, List<Long> deptIdList);
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<Long> queryDeptIdList(Long roleId);
	
}
