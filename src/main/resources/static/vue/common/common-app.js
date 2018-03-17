// 全局配置定义
const APP_NAME = "/triceps"; // 应用名称
const VUE_EL = "app"; // vue的el属性统一名称
const TABLE_ID = "grid"; // 显示表格的ID
const PAGE_NUMBER = 1; // 表格起始页码
const TABLE_PAGE_SIZE = 10; // 表格每页大小
const TABLE_PAGE_SIZE_LIST = [10, 25, 50, 100]; // 设置表格每页显示多少条

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