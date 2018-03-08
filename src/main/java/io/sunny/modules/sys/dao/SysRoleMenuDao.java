package io.sunny.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
