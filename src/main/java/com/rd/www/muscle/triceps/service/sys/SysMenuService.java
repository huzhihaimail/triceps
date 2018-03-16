
package com.rd.www.muscle.triceps.service.sys;

import com.rd.www.muscle.triceps.model.database.SysMenu;
import com.rd.www.muscle.triceps.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 菜单业务处理接口
 *
 * @author 胡志海
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 根据用户授权查询系统菜单
     *
     * @param userName 查询条件（用户名）
     * @return 授权菜单列表
     */
    List<SysMenu> loadMenus(String userName);

}
