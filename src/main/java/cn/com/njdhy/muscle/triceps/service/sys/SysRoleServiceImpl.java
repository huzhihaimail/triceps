
package cn.com.njdhy.muscle.triceps.service.sys;

import cn.com.njdhy.muscle.triceps.dao.SysRoleDao;
import cn.com.njdhy.muscle.triceps.model.database.SysRole;
import cn.com.njdhy.muscle.triceps.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 角色业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> loadRoles(Map<String, Object> userName) {
        return dao.loadRoles(userName);
    }
}
