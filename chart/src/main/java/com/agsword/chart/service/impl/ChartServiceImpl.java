package com.agsword.chart.service.impl;

import com.agsword.chart.service.IChartService;
import com.agsword.common.mapper.ChartMapper;
import com.agsword.common.entity.Chart;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图表信息表 服务实现类
 * </p>
 *
 * @author lyj
 * @since 2023-06-08
 */
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart> implements IChartService {

}
