package com.troytan.notify.dto;

import java.util.Date;

import com.troytan.notify.util.StringUtils;

public class NotifyDto {

    private Integer notifyId;
    private String  title;
    private String  content;
    private Date    sendOn;
    private Integer confirmCount;
    private Integer viewCount;
    private Integer CommentCount;
    private String  avatarUrl;
    private String  nickName;

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

    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendOn() {
        return sendOn;
    }

    public void setSendOn(Date sendOn) {
        this.sendOn = sendOn;
    }

    public Integer getConfirmCount() {
        return confirmCount;
    }

    public void setConfirmCount(Integer confirmCount) {
        this.confirmCount = confirmCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(Integer commentCount) {
        CommentCount = commentCount;
    }

}
