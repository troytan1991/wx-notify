/*
* Copyright 2018 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : itsr
*
* @File name : ReturnTemplate.java
*
* @Author : troytan
*
* @Date : 2018年7月23日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2018年7月23日    troytan    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/

package com.troytan.notify.exception;

/**
 * TODO description
 * 
 * @author troytan
 * @date 2018年7月23日
 */

public class ReturnTemplate {

    private String message;
    private Short  code;

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
