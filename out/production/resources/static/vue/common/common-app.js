// 定义项目根目录
const APP_NAME = "/triceps";
const VUE_EL="app";

/**
 * shiro权限
 */
function hasPermission(permission) {
    if (window.parent.permissions.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
}