package com.troytan.notify.dto;

import java.io.Serializable;

public class UserSessionDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer           userId;
    private String            openId;
    private String            sessionKey;

    public UserSessionDto(){
    }

    public UserSessionDto(Integer userId, String openId, String sessionKey){
        this.userId = userId;
        this.openId = openId;
        this.sessionKey = sessionKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

}
