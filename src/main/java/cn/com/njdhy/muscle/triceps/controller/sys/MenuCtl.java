
package cn.com.njdhy.muscle.triceps.controller.sys;

import com.github.pagehelper.PageInfo;
import cn.com.njdhy.muscle.triceps.model.common.Query;
import cn.com.njdhy.muscle.triceps.model.common.Result;
import cn.com.njdhy.muscle.triceps.model.database.SysMenu;
import cn.com.njdhy.muscle.triceps.service.sys.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
