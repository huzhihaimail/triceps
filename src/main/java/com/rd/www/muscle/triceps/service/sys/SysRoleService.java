
package com.rd.www.muscle.triceps.service.sys;

import com.rd.www.muscle.triceps.model.database.SysRole;
import com.rd.www.muscle.triceps.service.BaseService;

import java.util.List;

/**
 * <类功能简述> 任务调度业务处理接口
 *
 * @author 胡志海
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 加载角色列表
     *
     * @param userName 用户名称
     * @return 该用户所拥有的所有角色
     */
    List<SysRole> loadRoles(String userName);

}
