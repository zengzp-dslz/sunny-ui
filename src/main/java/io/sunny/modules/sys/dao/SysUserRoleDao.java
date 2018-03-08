package io.sunny.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.sys.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户与角色对应关系
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
