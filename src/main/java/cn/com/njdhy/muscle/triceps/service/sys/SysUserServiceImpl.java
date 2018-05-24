
package cn.com.njdhy.muscle.triceps.service.sys;

import cn.com.njdhy.muscle.triceps.dao.SysUserDao;
import cn.com.njdhy.muscle.triceps.model.database.SysUser;
import cn.com.njdhy.muscle.triceps.model.database.SysUserRole;
import cn.com.njdhy.muscle.triceps.service.BaseServiceImpl;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <类功能简述> 用户角色业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 插入用户
     *
     * @param sysUser 用户信息（包含角色信息）
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public void saveUser(SysUser sysUser) {

        // 获取密码盐
        String salt = new SecureRandomNumberGenerator().nextBytes(3).toHex();
        sysUser.setSalt(salt);

        // 获取密码
        String pwd = new SimpleHash("md5", sysUser.getPassword(), sysUser.getUserName() + salt, 3).toHex();
        sysUser.setPassword(pwd);

        // 用户信息入库
        dao.insert(sysUser);

        List<SysUserRole> sysUserRolesLst = new ArrayList<>();

        // 获取角色信息
        List<String> roles = sysUser.getUserRoles();
        // 获取用户ID
        String userId = sysUser.getId();

        for (String roleId : roles) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            sysUserRolesLst.add(sysUserRole);
        }

        // 用户配置角色信息入库
        sysUserRoleService.batchInsert(sysUserRolesLst);
    }


}
