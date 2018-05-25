/**
 * 表格显示列
 */
var showColumns = [
    {
        checkbox: true, width: "2%"
    }
    , {
        title: "序号",
        field: "id",
        width: "3%",
        align: "center",
        formatter: function (value, row, index) { // 设置列序号值，index从0开始
            return index + 1;
        }
    }
    , {
        field: "userName",
        title: "用户名称",
        width: "10%",
        sortable: true,
        sortName: "user_name" // sortName的值，需配置和数据库保持一致
    }
    , {
        field: "nickName",
        title: "昵称",
        width: "10%",
        sortable: true,
        sortName: "nickName"
    }
    , {
        field: "mobile",
        title: "手机号",
        width: "20%",
        sortable: true,
        sortName: "mobile"
    }
    , {
        field: "email",
        title: "邮箱",
        width: "15%",
        sortable: true,
        sortName: "email"
    }
    , {
        field: "createDate",
        title: "创建时间",
        width: "20%",
        formatter: function (value, row, index) {
            return new moment(value).format('YYYY-MM-DD HH:mm:ss');
        }
    }
    , {
        field: "updateDate",
        title: "最近修改时间",
        width: "20%",
        formatter: function (value, row, index) {
            return new moment(value).format('YYYY-MM-DD HH:mm:ss');
        }
    }
    /*, {
        field: "operate",
        title: "操作",
        width: "15%",
        formatter: function () {
            return '<a class="btn btn-success btn-sm" @click="save"><i class="fa fa-floppy-o"></i></a>\n' +
                '<a class="btn btn-warning btn-sm" @click="update"><i class="fa fa-pencil-square-o"></i></a>\n' +
                '<a class="btn btn-danger btn-sm" @click="del"><i class="fa fa-trash"></i></a>';
        }
    }*/
];


// 通用表格对象
var bsTable = new BootStrapTable();
// 如果有特殊表格需要处理，此处可以覆写覆写自己的表格属性 BootStrapTable.prototype.initBootstrapTable = function (columns, url, queryOpt) {}

// 定义vue实例
var vm = new Vue({
    el: "#" + VUE_EL
    , data: {

        /* 定义bootstrap-table表格参数 */
        queryOption: {}
        , columns: showColumns

        /* 定义页面操作参数 */
        , show: true// 切换页面中的查询和新建（编辑）页面
        , showPwd: true // 显示修改密码框
        , errorMessage: null // 异常信息
        , title: null // 标题
        , vueQueryParam: { // 查询参数
            keyword: null,
        }
        , model: {} //实体对象(用于新建、修改页面)
        , roles: [] // 加载角色列表对象
        , userRoles: [] // 用户选择的角色
    }
    // 定义方法
    , methods: {

        // 点击“查询”按钮事件
        query: function () {
            vm.reload();
        }

        // 点击“新增”按钮
        , save: function (event) {
            // 1. 隐藏表格，显示添加页面
            vm.show = false;
            vm.errorMessage = null;

            // 2. 设置标题
            vm.title = PAGE_INSERT_TITLE;
            // 3. 清空表单数据
            vm.model = {};

            // 4. 加载角色列表
            vm.loadRoles();
        }

        // 点击“确定”按钮
        , commit: function (el) {

            // 校验表单
            if (vm.model.userName == null || vm.model.userName == "") {
                vm.errorMessage = "请输入用户名";
                return;
            }

            // 执行新增操作
            if (vm.model.id == null) {
                vm.doSave();
                return;
            }

            // 执行修改操作
            vm.doUpdate();
        }

        // 执行保存操作
        , doSave: function () {

            // 获取到的用户配置的角色列表添加到后台参数
            vm.model.userRoles = vm.userRoles;

            // 2. 入库
            $.ajax({
                type: "POST",
                url: APP_NAME + "/sys/user/insert",
                contentType: "application/json",
                data: JSON.stringify(vm.model),
                success: function (r) {
                    if (r.code === 0) {
                        alert(PAGE_OPERATOR_SUCCESS, function (index) {
                            vm.reload();
                        });
                    } else if (r.code) {
                        alert(r.msg);
                    } else {
                        alert(r.msg);
                    }
                }
            });
        }

        // 显示修改页面
        , update: function () {

            // 隐藏密码框
            vm.showPwd = false;
            vm.errorMessage = null;

            // 获取所选择选择数据行的ID（可能选择多行）
            var ids = bsTable.getMultiRowIds();

            // 校验只能选择一行
            if (ids.length != 1) {
                alert(PAGE_SELECT_ONE);
                return;
            }

            $.get(APP_NAME + "/sys/user/" + ids[0], function (r) {
                vm.show = false;
                vm.title = PAGE_UPDATE_TITLE;
                vm.model = r.model;
            });
        }

        // 执行修改操作
        , doUpdate: function () {

            // 执行修改
            $.ajax({
                type: "POST",
                url: APP_NAME + "/sys/user/update",
                contentType: "application/json",
                data: JSON.stringify(vm.model),
                success: function (r) {
                    if (r.code === 0) {
                        alert(PAGE_OPERATOR_SUCCESS, function (index) {
                            vm.reload();
                        });
                    } else if (r.code) {
                        alert(r.msg);
                    } else {
                        alert(r.msg);
                    }
                }
            });
        }

        // 点击“删除”按钮
        , del: function (event) {

            // 获取选择记录ID
            var ids = bsTable.getMultiRowIds();

            // 校验未选择任何一行
            if (ids == null || ids.length <= 0) {
                alert(PAGE_SELECT_ONE);
                return;
            }

            confirm(PAGE_ARE_YOU_SURE_DEL, function () {
                $.ajax({
                    type: "POST",
                    url: APP_NAME + "/sys/user/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert(PAGE_OPERATOR_SUCCESS, function (index) {
                                vm.reload();
                            });
                        } else if (r.code) {
                            alert(r.msg);
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        }

        // 重新加载(ok)
        , reload: function () {

            // 展示查询列表
            vm.show = true;

            // 查询条件
            var queryOpt = {
                'keyword': vm.vueQueryParam.keyword == null ? "" : vm.vueQueryParam.keyword.trim(),
            };

            vm.queryOption = queryOpt;

            // 刷新表格数据
            bsTable.createBootStrapTable(showColumns, APP_NAME + "/sys/user/list?rnd=" + Math.random(), vm.queryOption);
        }

        // 加载角色列表
        , loadRoles: function () {
            $.get(APP_NAME + "/sys/user/loadRoles", function (r) {
                vm.roles = r.page;
            });
        }
    }
});

/**
 * 页面初始化执行
 */
$(function () {

    // 创建BootStrapTable
    bsTable.createBootStrapTable(vm.columns, APP_NAME + "/sys/user/list?rnd=" + Math.random(), vm.queryOption)
});




