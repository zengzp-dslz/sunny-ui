package io.sunny.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.sys.entity.SysDeptEntity;

import java.util.List;

/**
 * 部门管理
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);
}
