
package cn.com.njdhy.muscle.triceps.model.database;

/**
 * <类功能简述> 角色和权限关联实体
 *
 * @author 胡志海
 */
public class SysRolePrivilege {

    private String roleId;

    private String privilegeId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    @Override
    public String toString() {
        return "SysRolePrivilege{" +
                "roleId='" + roleId + '\'' +
                ", privilegeId='" + privilegeId + '\'' +
                '}';
    }
}
