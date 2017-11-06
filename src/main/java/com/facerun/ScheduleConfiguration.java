package com.facerun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleConfiguration {

    public static final Logger log = LoggerFactory.getLogger(ScheduleConfiguration.class);

    @Scheduled(cron = "0 50 * * * *"/* 每天早上1点0分开始执行定时任务。延时一个小时是为了消除数据库时间和应用服务器时间可能不一致。 */)
    public void updateDueOrders() {
        try {
            log.info("开始自动更新过期订单任务……");
        } catch (Exception e) {
            log.error("自动更新过期订单任务失败：{}", e.getLocalizedMessage());
        }
    }
}
