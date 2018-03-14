$(function () {

    var colModel = [
        {label: 'ID', name: 'id', width: 45, key: true, hidden: true}
        , {label: '用户名', name: 'userName', width: 80}
        , {label: '用户昵称', name: 'nickName', width: 80}
        , {label: '邮箱', name: 'email', width: 60}
        , {label: '手机号码', name: 'mobile', width: 100}
        , {label: '状态', name: 'status', width: 100}

    ];

    // 加载表格
    loadGrid(JQGRID_ID, APP_NAME + "/sys/user/list", colModel, JQGRID_PAGER);
});


// 定义vue实例
var vm = new Vue({
    el: "#" + VUE_EL,
    data: {
        flag: true, // 是否显示
        showPwd:true,
        title: null, // 标题
        queryParam: { // 查询参数
            keyword: null,
        },

        model: {}, //实体对象
    },
    // 定义方法
    methods: {

        // 点击“查询”按钮事件
        query: function () {
            vm.reload("query");
        },

        // 点击“新增”按钮
        save: function (event) {

            // 1. 隐藏表格，显示添加页面
            vm.flag = false;
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
        },

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
                            vm.reload("doSave");
                        });
                    } else if (r.code) {
                        alert(r.msg);
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },

        // 显示修改页面
        update: function () {

            // 隐藏密码
            vm.showPwd = false;

            // 获取选择记录ID
            var ids = jqGrid_getMultiRowIds();

            // 校验未选择任何一行
            if (ids == null || ids.length <= 0) {
                alert("请选择一条记录");
                return;
            }

            // 校验只能选择一行
            if (ids.length > 1) {
                alert("只能选择一条记录");
                return;
            }

            var id = ids[0];

            $.get(APP_NAME + "/sys/user/list/" + id, function (r) {
                vm.flag = false;
                vm.title = "修改";
                vm.model = r.model;
            });
        },

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
                            vm.reload("doUpdate");
                        });
                    } else if (r.code) {
                        alert(ERROR_CODE_MSG[r.code]);
                    } else {
                        alert(r.msg);
                    }
                }
            });

        },

        // 点击“删除”按钮
        del: function (event) {

            // 获取选择记录ID
            var ids = jqGrid_getMultiRowIds();

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
                                vm.reload("del");
                            });
                        } else if (r.code) {
                            alert(ERROR_CODE_MSG[r.code]);
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },

        // 重新加载
        reload: function (query_type) {

            // 展示查询列表
            vm.flag = true;

            // 获得当前的页数
            var page = query_type == "query" ? 1 : $("#jqGrid").jqGrid('getGridParam', 'page');

            // 查询条件
            var queryParams = {
                'keyword': vm.queryParam.keyword == null ? "" : vm.queryParam.keyword.trim(),
            };

            // .trigger() 说明：根据当前设置，重新载入Grid表格，即意味着向服务端重新发送一个新的请求。此方法只能用于已经构建好的Grid
            // setGridParam,说明：重新设置表格参数
            $("#jqGrid").jqGrid('setGridParam', {
                postData: queryParams,
                page: page
            }).trigger("reloadGrid");
        },
    }
});


// 加载grid
function loadGrid(gridId, url, colModel, pager) {
    $("#" + gridId).jqGrid({
        url: url,
        mtype: "POST",
        datatype: "json",
        colModel: colModel,
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: pager, // 定义分页栏

        // jsonReader来跟服务器端返回的数据做对应
        jsonReader: {
            root: "page.list",
            page: "page.pageNum",
            total: "page.pages",
            records: "page.total"
        },

        // 发送到服务端的参数
        prmNames: {
            page: "pageNum", // 当前页数
            rows: "pageSize", // 每页大小
            order: "order"
        },

        // 当选择行时触发此事件(rowid 表示id，status表是选择状态)
        onSelectRow: function (rowid, status) {
        },
        // 当表格所有数据都加载完成而且其他的处理也都完成时触发此事件，排序，翻页同样也会触发此事件
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
}
