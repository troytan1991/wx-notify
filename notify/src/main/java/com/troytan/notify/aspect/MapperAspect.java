package com.troytan.notify.aspect;

import org.apache.ibatis.datasource.DataSourceException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Mapper异常拦截切面： 转换数据库异常为通用异常
 * 
 * @author troytan E-mail:chaochao_tan@saic-gm.com
 * @version 创建时间：2017年5月12日 上午11:22:46
 */
@Component
@Aspect
public class MapperAspect {

    /**
     * 对repository下所有的mapper的方法抛异常后拦截
     * 
     * @author troytan
     * @Date 2017年5月12日
     * @param e
     * @throws Exception
     * @throws CommonException
     */
    @AfterThrowing(pointcut = "execution(* com.troytan.notify.repository.*.*(..))", throwing = "e")
    public void handleThrowing(Exception e) {
        // 参数e用于区分一般异常与数据库异常
        throw new DataSourceException("数据库执行异常", e);
    }
}
