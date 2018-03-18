/**
 * 获取当前选择多行ID
 */
function getMultiRowIds() {
    var ids = new Array();
    var rows = $table.bootstrapTable('getAllSelections');
    for (i = 0; i < rows.length; i++) {
        ids[i] = rows[i].id;
    }

    return ids;
}

/**
 * 取消所有选择
 */
function uncheckAll() {
    return $table.bootstrapTable('uncheckAll');
}


