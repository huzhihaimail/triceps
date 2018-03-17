
package com.rd.www.muscle.triceps.controller.sys;

import com.rd.www.muscle.triceps.model.common.Result;
import com.rd.www.muscle.triceps.model.database.SysMenu;
import com.rd.www.muscle.triceps.model.database.SysUser;
import com.rd.www.muscle.triceps.model.exception.ApplicationException;
import com.rd.www.muscle.triceps.service.sys.SysMenuService;
import com.rd.www.muscle.triceps.util.ShiroUtil;
import com.rd.www.muscle.triceps.util.errorcode.IndexErrorCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 首页控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/")
public class IndexCtl {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexCtl.class);

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 登陆系统
     *
     * @param sysUser 用户对象
     * @return 登陆结果
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(SysUser sysUser) {

        try {
            // 生成token
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getPassword());

            // 到realm中进行身份认证和鉴权
            SecurityUtils.getSubject().login(token);

        } catch (UnknownAccountException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_USERNAME_EMPTY_CODE, IndexErrorCode.SYS_INDEX_CTL_USERNAME_EMPTY_MESSAGE);
        } catch (IncorrectCredentialsException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_USER_PWD_WRONG_CODE, IndexErrorCode.SYS_INDEX_CTL_USER_PWD_WRONG_MESSAGE);
        } catch (LockedAccountException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_USER_LOCKED_CODE, IndexErrorCode.SYS_INDEX_CTL_USER_LOCKED_MESSAGE);
        } catch (AuthenticationException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_CHECK_USER_FAIL_CODE, IndexErrorCode.SYS_INDEX_CTL_CHECK_USER_FAIL_MESSAGE);
        } catch (Exception e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_CHECK_USER_FAIL_CODE, IndexErrorCode.SYS_INDEX_CTL_CHECK_USER_FAIL_MESSAGE);
        }

        return Result.success();
    }


    /**
     * 查询用户授权菜单
     *
     * @return 用户授权菜单列表
     */
    @RequestMapping("/loadMenus")
    public Result loadMenus() {

        try {

            String loginUserName = ShiroUtil.getLoginUserName();

            if (StringUtils.isEmpty(loginUserName)) {
                return Result.error(IndexErrorCode.SYS_INDEX_CTL_GET_USERNAME_FROM_SHIRO_ERROR_CODE, IndexErrorCode.SYS_INDEX_CTL_GET_USERNAME_FROM_SHIRO_ERROR_MESSAGE);
            }

            List<SysMenu> result = sysMenuService.loadMenus(loginUserName);

            if (CollectionUtils.isEmpty(result)) {
                result = new ArrayList<>();
            }

            return Result.success().put("page", result);

        } catch (ApplicationException e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ERROR_CODE, IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ROOT_ERROR_CODE, IndexErrorCode.SYS_INDEX_CTL_GET_MENUS_ROOT_ERROR_MESSAGE);
        }
    }

}
