package com.xzst.relation.mp.listener;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

@Component
public class MyServletRequestListener implements ServletRequestListener {
    private static final Logger logger=Logger.getLogger(MyServletRequestListener.class);
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        logger.info("此次访问结束");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        logger.info("此次访问开始");
    }
}
