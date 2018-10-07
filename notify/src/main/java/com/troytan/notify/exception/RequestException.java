/*
* Copyright 2018 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : itsr
*
* @File name : RequestException.java
*
* @Author : s8xriw
*
* @Date : 2018年7月31日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2018年7月31日    s8xriw    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/

package com.troytan.notify.exception;

/**
 * 请求异常,状态400
 * 
 * @author s8xriw
 * @date 2018年7月31日
 */

public class RequestException extends Exception {

    private static final long serialVersionUID = 664393041374310799L;

    public RequestException(){
        super();
    }

    public RequestException(String msg){
        super(msg);
    }

    public RequestException(String msg, Throwable e){
        super(msg, e);
    }

}
