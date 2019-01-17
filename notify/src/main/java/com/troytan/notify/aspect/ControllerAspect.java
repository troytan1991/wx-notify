/*
* Copyright 2017 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the troytan Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : sfol-agent-common
*
* @File name : ControllerAspect.java
*
* @Author : troytan
*
* @Date : 2017年7月31日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2017年7月31日    troytan    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/

package com.troytan.notify.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.troytan.notify.exception.RequestException;

/*
*
*拦截所有rest-Controller,soap-webservice注解的public方法，记录请求的起止标签
*
* @author troytan
* @date 2017年7月31日
*/
@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger("com.troytan.notify.bizLog");

    /**
     * 以@Controller或@WebSerivce注解，且为public方法
     *
     * @author troytan
     * @date 2017年7月31日
     */
    @Pointcut("execution(public * com.troytan.notify..*.*(..)) && "
              + "@within(org.springframework.web.bind.annotation.RestController)")
    public void processApi() {
        throw new UnsupportedOperationException();
    }

    @Before("processApi()")
    public void recordStartMarker(JoinPoint jp) {
        logger.info(getMarkerName(jp) + " start");
    }

    /**
     * 捕获bindresult属性处理
     *
     * @author troytan
     * @date 2018年7月31日
     * @param jp
     * @param result
     * @throws RequestException
     */
    @Before("processApi() && args(..,result)")
    public void catchBindError(JoinPoint jp, BindingResult result) throws RequestException {
        if (result.hasErrors()) {
            logger.info(getMarkerName(jp) + " start");
            StringBuilder sb = new StringBuilder();
            for (FieldError error : result.getFieldErrors()) {
                sb.append(error.getField() + " " + error.getDefaultMessage()).append(";");
            }
            throw new RequestException(sb.toString());
        }
    }

    @AfterReturning("processApi()")
    public void recordSuccEndMarker(JoinPoint jp) {
        logger.info(getMarkerName(jp) + " success end");
    }

    @AfterThrowing("processApi()")
    public void recordFailEndMarker(JoinPoint jp) {
        logger.warn(getMarkerName(jp) + " fail end");
    }

    /**
     * Class.method
     *
     * @author troytan
     * @date 2017年7月31日
     * @param jp
     * @return
     */
    private String getMarkerName(JoinPoint jp) {
        Signature signature = jp.getSignature();
        String typeName = signature.getDeclaringTypeName();
        return typeName.substring(typeName.lastIndexOf(".") + 1) + "." + signature.getName();
    }
}
