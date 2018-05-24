package cn.com.njdhy.muscle.triceps.service.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;


/**
 * <一句话功能简述> 强制退出
 * <功能详细描述>
 *
 * @author 胡志海
 * @version V0.0.1-SNAPSHOT
 */
public class ForceLogoutFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Session session = getSubject(request, response).getSession(false);
        if (session == null) {
            return true;
        }
        return session.getAttribute("session.force.logout") == null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        try {
            //强制退出
            getSubject(request, response).logout();
            response.getWriter().write("{'ret':302,'msg':'','redirectUrl':'login.html'}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
        WebUtils.issueRedirect(request, response, loginUrl);
        return false;
    }
}