package io.sunny.modules.sys.service.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sunny.common.annotation.DataFilter;
import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sys.dao.SysUserDao;
import io.sunny.modules.sys.entity.SQLtest;
import io.sunny.modules.sys.entity.SysUserEntity;
import io.sunny.modules.sys.service.SysUserRoleService;
import io.sunny.modules.sys.service.SysUserService;
import io.sunny.modules.sys.shiro.ShiroUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Override
	public List<String> queryAllPerms(Long userId) {
		return sysUserDao.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return sysUserDao.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return sysUserDao.queryByUserName(username);
	}

	@Override
	public SysUserEntity queryObject(Long userId) {
		return sysUserDao.queryObject(userId);
	}

	@Override
	@DataFilter(tableAlias = "u", user = false)
	public List<SysUserEntity> queryList(Map<String, Object> map) {
		return sysUserDao.queryList(map);
	}

	@Override
	@DataFilter(tableAlias = "u", user = false)
	public int queryTotal(Map<String, Object> map) {
		return sysUserDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		// sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setSalt(salt);
		user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
		sysUserDao.save(user);

		// 保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		if (StringUtils.isBlank(user.getPassword())) {
			user.setPassword(null);
		} else {
			user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
		}
		sysUserDao.update(user);

		// 保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] userId) {
		sysUserDao.deleteBatch(userId);
	}

	@Override
	public int updatePassword(Long userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return sysUserDao.updatePassword(map);
	}

	/**
	 * 指定数据源
	 *
	 * @return
	 * @author SHANHY
	 * @create 2016年1月24日
	 */
	@Override
	@TargetDataSource(name = "sunny")
	public List<SQLtest> getListByDs2() {
		String sql = "SELECT goods_id,goods_name FROM sunnygoods limit 1";
		return jdbcTemplate.query(sql, new RowMapper<SQLtest>() {

			@Override
			public SQLtest mapRow(ResultSet rs, int rowNum) throws SQLException {
				SQLtest stu = new SQLtest();
				stu.setInfo(rs.getString("goods_id"));
				stu.setTime(rs.getString("goods_name"));
				return stu;
			}

		});

	}
}
