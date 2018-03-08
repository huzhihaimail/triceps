/*
 * 公司名称：北京掌众金融服务有限公司
 * 版权信息：北京掌众金融服务有限公司 版权所有
 * 文件名称：ErrorCode.java
 * 创建时间：2018/3/7 15:26
 * 创建人：胡志海
 * 修改内容：
 * 修改人：
 * 修改单号 ：
 */
package com.rd.www.muscle.triceps.util;

/**
 * <类功能简述> 异常码常量类
 *
 * @author 胡志海
 */
public interface ErrorCodeUtil {

    /**
     * 首页
     */
    String SYS_INDEX = "1000";

    /**
     * 用户名为空
     */
    String SYS_INDEX_CTL_USERNAME_EMPTY_CODE = SYS_INDEX + "1";
    String SYS_INDEX_CTL_USERNAME_EMPTY_MESSAGE = "用户名为空";

    /**
     * 密码错误
     */
    String SYS_INDEX_CTL_USER_PWD_WRONG_CODE = SYS_INDEX + "2";
    String SYS_INDEX_CTL_USER_PWD_WRONG_MESSAGE = "密码错误";

    /**
     * 账号被锁定
     */
    String SYS_INDEX_CTL_USER_LOCKED_CODE = SYS_INDEX + "3";
    String SYS_INDEX_CTL_USER_LOCKED_MESSAGE = "账号被锁定";

    /**
     * 身份校验失败
     */
    String SYS_INDEX_CTL_CHECK_USER_FAIL_CODE = SYS_INDEX + "4";
    String SYS_INDEX_CTL_CHECK_USER_FAIL_MESSAGE = "身份校验失败";


}
