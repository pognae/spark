package com.wowpmd.util.scheduler;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wowpmd.common.RequestImpl;
import com.wowpmd.common.service.SystemService;

@Component
public class MemoryScheduler {
    
    final Log log = LogFactory.getLog(MemoryScheduler.class);
    
    final static long sec = 1000;
    
    final static long min = sec * 60;
    
    final static long hour = min * 60;
    
    final static long day = hour * 24;

    @Inject
    private SystemService systemService;
    
//    @Scheduled(fixedDelay = min * 30 + sec * 5)
    @Scheduled(fixedDelay = hour * 3 + sec * 5)
    public void reloadCode() {
        this.systemService.reloadCodeHandler();
    }
    
    
}
