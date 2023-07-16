package com.agsword.chart.vo;

import lombok.Data;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.example.chart.vo
 * @className: BiResponse
 * @author: LiYinjian
 * @date: 2022/5/5 1:01
 * @version: 1.0
 */

@Data
public class BiResponse {

    private String genChart;

    private String genResult;

    private Long chartId;
}
