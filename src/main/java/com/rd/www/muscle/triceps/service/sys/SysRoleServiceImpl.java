
package com.rd.www.muscle.triceps.service.sys;

import com.rd.www.muscle.triceps.dao.SysRoleDao;
import com.rd.www.muscle.triceps.model.database.SysRole;
import com.rd.www.muscle.triceps.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <类功能简述> 角色业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> loadRoles(String userName) {
        return dao.loadRoles(userName);
    }
}
