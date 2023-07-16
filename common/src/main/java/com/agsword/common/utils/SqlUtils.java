package com.agsword.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.common.utils
 * @className: SqlUtils
 * @author: LiYinjian
 * @date: 2022/5/5 1:01
 * @version: 1.0
 */

public class SqlUtils {
    /**
     * 校验排序字段是否合法（防止 SQL 注入）
     *
     * @param sortField
     * @return
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }
}
