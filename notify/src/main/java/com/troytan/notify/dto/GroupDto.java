package com.troytan.notify.dto;

public class GroupDto {

    private String  encryptedData; // group加密数据
    private String  iv;            // 初始向量
    private Integer notifyId;      // 通知单ID

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

}
