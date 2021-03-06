package com.troytan.notify.domain;

import java.util.Date;

public class Form {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_form.ID
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_form.USER_ID
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_form.FORM_ID
     *
     * @mbg.generated
     */
    private String formId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_form.CREATE_BY
     *
     * @mbg.generated
     */
    private Integer createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_form.CREATE_ON
     *
     * @mbg.generated
     */
    private Date createOn;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_form.ID
     *
     * @return the value of tt_form.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_form.ID
     *
     * @param id the value for tt_form.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_form.USER_ID
     *
     * @return the value of tt_form.USER_ID
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_form.USER_ID
     *
     * @param userId the value for tt_form.USER_ID
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_form.FORM_ID
     *
     * @return the value of tt_form.FORM_ID
     *
     * @mbg.generated
     */
    public String getFormId() {
        return formId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_form.FORM_ID
     *
     * @param formId the value for tt_form.FORM_ID
     *
     * @mbg.generated
     */
    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_form.CREATE_BY
     *
     * @return the value of tt_form.CREATE_BY
     *
     * @mbg.generated
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_form.CREATE_BY
     *
     * @param createBy the value for tt_form.CREATE_BY
     *
     * @mbg.generated
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_form.CREATE_ON
     *
     * @return the value of tt_form.CREATE_ON
     *
     * @mbg.generated
     */
    public Date getCreateOn() {
        return createOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_form.CREATE_ON
     *
     * @param createOn the value for tt_form.CREATE_ON
     *
     * @mbg.generated
     */
    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}