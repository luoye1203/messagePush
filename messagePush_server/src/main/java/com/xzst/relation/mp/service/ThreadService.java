package com.xzst.relation.mp.service;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ThreadService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Async("e1")
    public void test(){
        try {
           int i= (int)(Math.random() * 10000);
           logger.info("xxxx:"+i);
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("hah");

    }
}
