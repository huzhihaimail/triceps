
package com.rd.www.muscle.triceps.controller.sys;

import com.rd.www.muscle.triceps.model.common.Result;
import com.rd.www.muscle.triceps.model.database.SysUser;
import com.rd.www.muscle.triceps.util.ErrorCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <类功能简述> 首页控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/")
public class IndexCtl {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexCtl.class);

    @RequestMapping("/index")
    public String index() {
        return "hi ,index.";
    }

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
            return Result.error(ErrorCodeUtil.SYS_INDEX_CTL_USERNAME_EMPTY_CODE, ErrorCodeUtil.SYS_INDEX_CTL_USERNAME_EMPTY_MESSAGE);
        } catch (IncorrectCredentialsException e) {
            return Result.error(ErrorCodeUtil.SYS_INDEX_CTL_USER_PWD_WRONG_CODE, ErrorCodeUtil.SYS_INDEX_CTL_USER_PWD_WRONG_MESSAGE);
        } catch (LockedAccountException e) {
            return Result.error(ErrorCodeUtil.SYS_INDEX_CTL_USER_LOCKED_CODE, ErrorCodeUtil.SYS_INDEX_CTL_USER_LOCKED_MESSAGE);
        } catch (AuthenticationException e) {
            return Result.error(ErrorCodeUtil.SYS_INDEX_CTL_CHECK_USER_FAIL_CODE, ErrorCodeUtil.SYS_INDEX_CTL_CHECK_USER_FAIL_MESSAGE);
        } catch (Exception e) {
            return Result.error(ErrorCodeUtil.SYS_INDEX_CTL_CHECK_USER_FAIL_CODE, ErrorCodeUtil.SYS_INDEX_CTL_CHECK_USER_FAIL_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 添加身份校验权限
     *
     * @return
     */
    @RequestMapping("/unauthorized")
    public String unauthorizedUrl() {
        return "hi ,unauthorized.";
    }


}
