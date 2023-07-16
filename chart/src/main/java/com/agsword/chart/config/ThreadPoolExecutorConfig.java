package com.agsword.chart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.chart.config
 * @className: ThreadPoolExecutorConfig
 * @author: LiYinjian
 * @date: 2023/7/7 18:46
 * @version: 1.0
 */

@Configuration
public class ThreadPoolExecutorConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadFactory threadFactory = new ThreadFactory(){
            private int count = 1;

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = newThread(r);
                thread.setName("线程"+count);
                count++;
                return thread;
            }
        };
        // 核心线程数为2（部署的机器为单核（N=1），线程任务为io，线程池线程数为2*N）
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                4, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), threadFactory);
        return threadPoolExecutor;
    }

}
