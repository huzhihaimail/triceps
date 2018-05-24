
package cn.com.njdhy.muscle.triceps.service.sys;

import cn.com.njdhy.muscle.triceps.model.database.SysRole;
import cn.com.njdhy.muscle.triceps.service.BaseService;

import java.util.List;
import java.util.Map;

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
    List<SysRole> loadRoles(Map<String, Object> userName);

}
