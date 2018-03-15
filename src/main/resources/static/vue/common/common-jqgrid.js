$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

/**
 * 获取当前选择多行ID
 */
function jqGrid_getMultiRowIds() {
    return $("#" + JQGRID_ID).getGridParam("selarrrow");
}

/**
 * 获取当前选择行的ID
 */
function jqGrid_getSingleRowId() {
    return $("#" + JQGRID_ID).getGridParam("selrow");
}

/**
 * 获取当前页码
 */
function jqGrid_getCurrPage() {
    return $("#" + JQGRID_ID).jqGrid('getGridParam', 'page');
}


//选择多条记录
function checkSelectMultiRows() {

    if (!jqGrid_getSingleRowId()) {
        alert("请选择一条记录");
        return;
    }

    return jqGrid_getMultiRowIds();
}