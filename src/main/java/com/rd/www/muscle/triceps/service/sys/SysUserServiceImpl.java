
package com.rd.www.muscle.triceps.service.sys;

import com.rd.www.muscle.triceps.dao.SysUserDao;
import com.rd.www.muscle.triceps.model.database.SysUser;
import com.rd.www.muscle.triceps.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <类功能简述> 用户管理业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUser> implements SysUserService {

    /**
     * 函数功能描述：插入操作示例
     *
     * @param sysUser 示例实体对象
     * @return 影响行数
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Integer saveUser(SysUser sysUser) {

        
        return 0;
    }


}
