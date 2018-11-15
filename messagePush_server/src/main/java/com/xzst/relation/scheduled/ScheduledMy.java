package com.xzst.relation.scheduled;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledMy {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Scheduled(initialDelay =1000*10, fixedDelay =1000*30)
    public  void fixDelayTest(){
        logger.info("fixedDelay 定时任务");
    }
    @Scheduled(initialDelay =1000*10, fixedRate =1000*60)
    public  void fixRateTest(){
        logger.info("fixedRate 定时任务");
    }

    @Scheduled(cron = "0 36 * * * ?")
    public  void cronTest(){
        logger.info("cron 定时任务");
    }
}
