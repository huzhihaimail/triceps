var $table;

/**
 * 页面初始化执行
 */
$(function () {
    // 创建BootStrapTable
    createBootStrapTable();
});


/**
 * 创建表格
 */
function createBootStrapTable(queryOpt) {
    // 销毁表格数据
    destoryBootStrapTable();
    // 初始化表格插件
    initBootstrapTable(queryOpt);
}


// 定义vue实例
var vm = new Vue({
    el: "#" + VUE_EL,
    data: {
        show: true// 切换页面中的查询和新建（编辑）页面
        , showPwd: true // 显示修改密码框
        , title: null // 标题
        , vueQueryParam: { // 查询参数
            keyword: null,
        }
        , model: {} //实体对象(用于新建、修改页面)
    },
    // 定义方法
    methods: {
        // 点击“查询”按钮事件
        query: function () {
            vm.reload();
        },

        // 点击“新增”按钮
        save: function (event) {
            // 1. 隐藏表格，显示添加页面
            vm.show = false;
            // 2. 设置标题
            vm.title = "新增";
            // 3. 清空表单数据
            vm.model = {};
        },

        // 点击“确定”按钮
        commit: function () {

            // 执行新增操作
            if (vm.model.id == null) {
                vm.doSave();
                return;
            }

            // 执行修改操作
            vm.doUpdate();
        }
        ,

        // 执行保存操作
        doSave: function () {

            // 2. 入库
            $.ajax({
                type: "POST",
                url: APP_NAME + "/sys/user/insert",
                contentType: "application/json",
                data: JSON.stringify(vm.model),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
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
        ,

        // 显示修改页面
        update: function () {

            // 隐藏密码
            vm.showPwd = false;

            // 校验只能选择一行
            if (getMultiRowIds().length != 1) {
                alert("请选择一条记录");
                return;
            }

            $.get(APP_NAME + "/sys/user/" + ids[0], function (r) {
                vm.show = false;
                vm.title = "修改";
                vm.model = r.model;
            });
        }
        ,

        // 执行修改操作
        doUpdate: function () {

            // 执行修改
            $.ajax({
                type: "POST",
                url: APP_NAME + "/sys/user/update",
                contentType: "application/json",
                data: JSON.stringify(vm.model),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else if (r.code) {
                        alert(r.msg);
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },

        // 点击“删除”按钮
        del: function (event) {

            // 获取选择记录ID
            var ids = getMultiRowIds();

            // 校验未选择任何一行
            if (ids == null || ids.length <= 0) {
                alert("请选择一条记录");
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: APP_NAME + "/sys/user/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
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
        },

        // 重新加载(ok)
        reload: function () {

            // 展示查询列表
            vm.show = true;

            // 查询条件
            var queryOpt = {
                'keyword': vm.vueQueryParam.keyword == null ? "" : vm.vueQueryParam.keyword.trim(),
            };

            // 刷新表格数据
            createBootStrapTable(queryOpt);
        },
    }
});

/**
 * 加载列
 */
function initColumns() {

    var columns = [{checkbox: true, width: "2%"},
        {
            title: "序号",
            field: "id",
            width: "2%",
            align: "center",
            formatter: function (value, row, index) { // 设置列序号值，index从0开始
                return index + 1;
            }
        }
        , {
            field: "userName",
            title: "用户名称",
            width: "20%",
            sortable: true,
            sortName: "user_name" // sortName的值，需配置和数据库保持一致
        }
        , {
            field: "nickName",
            title: "昵称",
            width: "20%",
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
            width: "40%",
            sortable: true,
            sortName: "email"
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

    return columns;
}


/**
 * 销毁表格数据
 */
function destoryBootStrapTable() {
    $('#' + TABLE_ID).bootstrapTable('destroy');
}


/**
 * 加载表格
 * @param defineQueryParams 用户自定义查询参数（传递到后台）
 */
function initBootstrapTable(queryOpt) {
    $table = $('#' + TABLE_ID).bootstrapTable({
        columns: initColumns(), // 设置查询结果展示列
        url: APP_NAME + "/sys/user/list?rnd=" + Math.random(),
        //data:data,
        height: $(window).height() - 50 - 30,
        toolbar: '#toolbar', // 显示工具按钮
        showRefresh: true, // 显示刷新按钮
        showPaginationSwitch: true, // 显示和隐藏状态栏按钮
        striped: true, // 表格数据隔行换色
        pagination: true, // 显示分页按钮
        paginationHAlign: "right",
        pageNumber: PAGE_NUMBER,
        pageSize: TABLE_PAGE_SIZE,
        pageList: TABLE_PAGE_SIZE_LIST,
        sidePagination: "server", // 后台分页
        paginationLoop: false, // 分页不循环
        clickToSelect: true, // 点击选中一行
        checkboxHeader: true, // 显示和隐藏多选checkbox
        sortable: true, //隐藏和显示所有排序按钮
        sortOrder: "asc", // 排序类型
        showColumns: true,
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数

            // 设置表格默认参数
            var bstParam = {
                pageNumber: params.pageNumber
                , pageSize: params.pageSize
                , sortName: params.sortName
                , sortOrder: params.sortOrder
                , searchText: params.searchText
            }

            if (!queryOpt) {
                return bstParam;
            }

            // 用户自定义查询参数
            for (var key in queryOpt) {
                bstParam[key] = queryOpt[key];
            }

            return bstParam;
        },
    });
}

