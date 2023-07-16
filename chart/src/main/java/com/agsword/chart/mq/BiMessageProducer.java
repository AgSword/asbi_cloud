package com.agsword.chart.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.chart.mq
 * @className: BiMessageProducer
 * @author: LiYinjian
 * @date: 2023/7/10 18:22
 * @version: 1.0
 */

@Component
public class BiMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(BiMqConstant.BI_EXCHANGE_NAME, BiMqConstant.BI_ROUTING_KEY, message);
    }
}
