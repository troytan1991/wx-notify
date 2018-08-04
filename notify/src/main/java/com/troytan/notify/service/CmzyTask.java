package com.troytan.notify.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.troytan.notify.manager.CmzyManager;

@Component
public class CmzyTask {

    private static final Logger log = LoggerFactory.getLogger(CmzyTask.class);

    @Autowired
    private CmzyManager         cmzyManager;

    /**
     * 每天0点执行，更新文章信息
     *
     * @author troytan
     * @date 2018年6月26日
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateNews() {
        try {
            cmzyManager.updateNews();
        } catch (Exception e) {
            log.error("Json转换失败", e);
        }
    }
}
