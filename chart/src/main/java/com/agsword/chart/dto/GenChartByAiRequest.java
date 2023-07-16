package com.agsword.chart.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.example.chart.dto
 * @className: GenChartByAiRequest
 * @author: LiYinjian
 * @date: 2022/5/5 1:01
 * @version: 1.0
 */

@Data
public class GenChartByAiRequest implements Serializable {

    private static final long serialVersionUID = 8009182885625282019L;
    /**
     * 名称
     */
    private String name;

    /**
     * 分析目标
     */
    private String goal;

    /**
     * 图标类型
     */
    private String chartType;
}
