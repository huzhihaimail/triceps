
package cn.com.njdhy.muscle.triceps.util;

import cn.com.njdhy.muscle.triceps.model.database.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * <类功能简述> shiro 工具类
 *
 * @author 胡志海
 */
public class ShiroUtil {

    /**
     * 获取用户名
     *
     * @return 登陆用户名
     */
    public static String getLoginUserName() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return user.getUserName();
    }


}
