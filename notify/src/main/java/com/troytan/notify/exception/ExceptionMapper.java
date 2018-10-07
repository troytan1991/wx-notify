/*
* Copyright 2018 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : itsr
*
* @File name : ExceptionMapper.java
*
* @Author : s8xriw
*
* @Date : 2018年7月23日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2018年7月23日    s8xriw    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/

package com.troytan.notify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 异常统一处理类,继承基础类,捕获所有@RestMapper下的方法抛出的异常,进行自定义返回对象
 * 
 * @author s8xriw
 * @date 2018年7月23日
 */
@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleMyException(Exception ex, WebRequest request) {
        ReturnTemplate rt = new ReturnTemplate();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (ex instanceof RequestException) {
            rt.setCode((short) 400);
            rt.setMessage(ex.getMessage());
        } else if (ex instanceof UnauthException) {
            rt.setCode((short) 401);
            rt.setMessage(ex.getMessage());
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex instanceof BusinessException) {
            rt.setCode((short) 500);
            rt.setMessage("系统业务异常");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            rt.setCode((short) 500);
            rt.setMessage(ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return handleExceptionInternal(ex, rt, null, status, request);
    }
}
