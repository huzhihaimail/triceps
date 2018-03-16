
package com.rd.www.muscle.triceps.service.sys;

import com.rd.www.muscle.triceps.dao.SysMenuDao;
import com.rd.www.muscle.triceps.model.database.SysMenu;
import com.rd.www.muscle.triceps.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 菜单业务层实现类
 *
 * @author 胡志海
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> loadMenus(String userName) {
        return dao.loadMenus(userName);
    }
}
