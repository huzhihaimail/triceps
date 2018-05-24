
package cn.com.njdhy.muscle.triceps.dao;

import cn.com.njdhy.muscle.triceps.model.database.SysMenu;

import java.util.List;

/**
 * <类功能简述> 定时任务数据访问层接口
 *
 * @author 胡志海
 */
public interface SysMenuDao extends BaseDao<SysMenu> {

    /**
     * 根据用户授权查询系统菜单
     *
     * @param userName 查询条件（用户名）
     * @return 授权菜单列表
     */
    List<SysMenu> loadMenus(String userName);

}
