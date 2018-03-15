
package com.rd.www.muscle.triceps.controller.sys;

import com.github.pagehelper.PageInfo;
import com.rd.www.muscle.triceps.model.common.Query;
import com.rd.www.muscle.triceps.model.common.Result;
import com.rd.www.muscle.triceps.model.database.SysUser;
import com.rd.www.muscle.triceps.model.exception.ApplicationException;
import com.rd.www.muscle.triceps.service.sys.SysUserService;
import com.rd.www.muscle.triceps.util.errorcode.UserErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 首页控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/sys/user")
public class UserCtl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCtl.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询用户列表
     *
     * @param params   参数列表
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return 用户列表
     */
    @RequestMapping("/list")
    public Result index(@RequestParam Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Query queryParam = new Query(params);
        PageInfo<SysUser> result = sysUserService.queryList(queryParam, pageNum, pageSize);

        return Result.success().put("page", result);
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 用户ID
     * @return 用户实体
     */
    @RequestMapping("/list/{id}")
    public Result queryById(@PathVariable String id) {

        SysUser model = sysUserService.queryById(id);

        if (ObjectUtils.isEmpty(model)) {
            model = new SysUser();
        }

        return Result.success().put("model", model);
    }


    /**
     * 保存
     *
     * @param sysUser 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/insert")
    public Result insert(@RequestBody SysUser sysUser) {

        try {
            // 校验参数
            // TODO: 2018/3/14

            // 执行入库操作
            sysUserService.insert(sysUser);
        } catch (ApplicationException e) {
            return Result.error(UserErrorCode.SYS_USER_SAVE_APP_ERROR_CODE, UserErrorCode.SYS_USER_SAVE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(UserErrorCode.SYS_USER_SAVE_ERROR_CODE, UserErrorCode.SYS_USER_SAVE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 修改操作
     *
     * @param sysUser 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysUser sysUser) {

        try {
            // 校验参数
            // TODO: 2018/3/14

            // 执行修改
            sysUserService.update(sysUser);
        } catch (ApplicationException e) {
            return Result.error(UserErrorCode.SYS_USER_UPDATE_APP_ERROR_CODE, UserErrorCode.SYS_USER_UPDATE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(UserErrorCode.SYS_USER_UPDATE_ERROR_CODE, UserErrorCode.SYS_USER_UPDATE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 删除多个记录
     *
     * @param ids 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/delete")
    public Result deleteByIds(@RequestBody List<String> ids) {

        try {
            // 校验参数 todo
            sysUserService.deleteByIds(ids);
        } catch (ApplicationException e) {
            return Result.error(e.getCode(), e.getMsg());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

        return Result.success();
    }

}
