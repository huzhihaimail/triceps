
package com.rd.www.muscle.triceps.controller.sys;

import com.github.pagehelper.PageInfo;
import com.rd.www.muscle.triceps.model.common.Query;
import com.rd.www.muscle.triceps.model.common.Result;
import com.rd.www.muscle.triceps.model.database.SysMenu;
import com.rd.www.muscle.triceps.service.sys.SysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 菜单控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuCtl {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuCtl.class);

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 分页查询菜单列表
     *
     * @param params   查询参数
     * @param pageNum  当前页码
     * @param pageSize 每页显示大小
     * @return 列表
     */
    @RequestMapping("/list")
    public Result index(@RequestParam Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Query queryParam = new Query(params);
        PageInfo<SysMenu> result = sysMenuService.queryList(queryParam, pageNum, pageSize);

        return Result.success().put("page", result);
    }

}
