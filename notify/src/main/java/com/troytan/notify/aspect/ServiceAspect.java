package com.troytan.notify.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * service层切面: 拦截service public方法,抛错后记录日志
 *
 * @author troytan E-mail:chaochao_tan@saic-gm.com
 * @version 创建时间：2017年5月12日 下午2:08:54
 */
@Aspect
@Component
public class ServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger("com.troytan.notify.errorLog");

    @AfterThrowing(pointcut = "execution(public * com.troytan.notify..*.*(..)) && @within(org.springframework.stereotype.Service)", throwing = "e")
    public void recordModuleLog(JoinPoint jp, Exception e) throws Throwable {
        // 数据库异常与未知异常记录error日志
        logger.error("method:{}--input params:{}", jp.getSignature(), jp.getArgs());
        logger.error("", e);

        throw e;
    }

}
