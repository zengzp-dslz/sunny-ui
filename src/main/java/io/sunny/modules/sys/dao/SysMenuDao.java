package io.sunny.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.sys.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysMenuEntity> queryUserList(Long userId);
}
