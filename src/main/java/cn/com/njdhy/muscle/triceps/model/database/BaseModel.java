
package cn.com.njdhy.muscle.triceps.model.database;

import java.util.Date;

/**
 * <类功能简述> 通用数据表字段
 *
 * @author 胡志海
 */
public class BaseModel {

    /**
     * 数据行ID
     */
    private String id;

    /**
     * 逻辑删除标志
     */
    private String deleteFlag;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id='" + id + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
