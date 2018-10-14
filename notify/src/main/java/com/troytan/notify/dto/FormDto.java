package com.troytan.notify.dto;

/**
 * 提醒用户的提交码
 * 
 * @author troytan
 * @date 2018年10月12日
 */
public class FormDto {

    private Integer id;
    private String  openId;
    private String  formId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

}
