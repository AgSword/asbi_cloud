package com.agsword.common.common;

import com.agsword.common.constant.CommonConstant;
import lombok.Data;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.common.dto
 * @className: PageRequest
 * @author: LiYinjian
 * @date: 2023/6/2 1:01
 * @version: 1.0
 */

@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private long current = 1;

    /**
     * 页面大小
     */
    private long pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
