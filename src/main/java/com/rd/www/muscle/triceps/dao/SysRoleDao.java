
package com.rd.www.muscle.triceps.dao;

import com.rd.www.muscle.triceps.model.database.SysRole;

import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 角色数据访问层接口
 *
 * @author 胡志海
 */
public interface SysRoleDao extends BaseDao<SysRole> {

    /**
     * 加载角色列表
     *
     * @param userName 用户名称
     * @return 该用户所拥有的所有角色
     */
    List<SysRole> loadRoles(Map<String, Object> userName);
}
