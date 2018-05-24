
package cn.com.njdhy.muscle.triceps.service.sys;

import cn.com.njdhy.muscle.triceps.model.database.SysMenu;
import cn.com.njdhy.muscle.triceps.service.BaseService;

import java.util.List;

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
