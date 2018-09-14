package com.troytan.notify.dto;

import java.util.Date;

import com.troytan.notify.util.StringUtils;

public class ConfirmDto {

    private String avatarUrl;
    private String nickName;
    private Date   updateOn;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return StringUtils.base64Decode(nickName);
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

}
