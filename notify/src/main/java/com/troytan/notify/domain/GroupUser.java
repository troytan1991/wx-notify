package com.troytan.notify.domain;

import java.util.Date;

public class GroupUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_group_user.GROUP_ID
     *
     * @mbg.generated
     */
    private String groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_group_user.OPEN_ID
     *
     * @mbg.generated
     */
    private String openId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_group_user.NICKNAME
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_group_user.STATUS
     *
     * @mbg.generated
     */
    private Short status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_group_user.CREATE_BY
     *
     * @mbg.generated
     */
    private Integer createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_group_user.CREATE_ON
     *
     * @mbg.generated
     */
    private Date createOn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_group_user.UPDATE_BY
     *
     * @mbg.generated
     */
    private Integer updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_group_user.UPDATE_ON
     *
     * @mbg.generated
     */
    private Date updateOn;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_group_user.GROUP_ID
     *
     * @return the value of tt_group_user.GROUP_ID
     *
     * @mbg.generated
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_group_user.GROUP_ID
     *
     * @param groupId the value for tt_group_user.GROUP_ID
     *
     * @mbg.generated
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_group_user.OPEN_ID
     *
     * @return the value of tt_group_user.OPEN_ID
     *
     * @mbg.generated
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_group_user.OPEN_ID
     *
     * @param openId the value for tt_group_user.OPEN_ID
     *
     * @mbg.generated
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_group_user.NICKNAME
     *
     * @return the value of tt_group_user.NICKNAME
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_group_user.NICKNAME
     *
     * @param nickname the value for tt_group_user.NICKNAME
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_group_user.STATUS
     *
     * @return the value of tt_group_user.STATUS
     *
     * @mbg.generated
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_group_user.STATUS
     *
     * @param status the value for tt_group_user.STATUS
     *
     * @mbg.generated
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_group_user.CREATE_BY
     *
     * @return the value of tt_group_user.CREATE_BY
     *
     * @mbg.generated
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_group_user.CREATE_BY
     *
     * @param createBy the value for tt_group_user.CREATE_BY
     *
     * @mbg.generated
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_group_user.CREATE_ON
     *
     * @return the value of tt_group_user.CREATE_ON
     *
     * @mbg.generated
     */
    public Date getCreateOn() {
        return createOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_group_user.CREATE_ON
     *
     * @param createOn the value for tt_group_user.CREATE_ON
     *
     * @mbg.generated
     */
    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_group_user.UPDATE_BY
     *
     * @return the value of tt_group_user.UPDATE_BY
     *
     * @mbg.generated
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_group_user.UPDATE_BY
     *
     * @param updateBy the value for tt_group_user.UPDATE_BY
     *
     * @mbg.generated
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_group_user.UPDATE_ON
     *
     * @return the value of tt_group_user.UPDATE_ON
     *
     * @mbg.generated
     */
    public Date getUpdateOn() {
        return updateOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_group_user.UPDATE_ON
     *
     * @param updateOn the value for tt_group_user.UPDATE_ON
     *
     * @mbg.generated
     */
    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }
}