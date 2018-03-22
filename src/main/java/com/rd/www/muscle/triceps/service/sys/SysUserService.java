
package com.rd.www.muscle.triceps.service.sys;

import com.rd.www.muscle.triceps.model.database.SysUser;
import com.rd.www.muscle.triceps.service.BaseService;

/**
 * <类功能简述> 任务调度业务处理接口
 *
 * @author 胡志海
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 保存用户和角色信息到数据库
     *
     * @param sysUser 用户信息（包含角色信息）
     * @return
     */
    void saveUser(SysUser sysUser);

}
