package com.troytan.notify.domain;

public class Work {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_work.WORK_ID
     *
     * @mbg.generated
     */
    private Integer workId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_work.IMG_URL
     *
     * @mbg.generated
     */
    private String imgUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_work.DESIGNER_ID
     *
     * @mbg.generated
     */
    private Long designerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_work.IS_COVER
     *
     * @mbg.generated
     */
    private Boolean isCover;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_work.WORK_ID
     *
     * @return the value of tt_work.WORK_ID
     *
     * @mbg.generated
     */
    public Integer getWorkId() {
        return workId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_work.WORK_ID
     *
     * @param workId the value for tt_work.WORK_ID
     *
     * @mbg.generated
     */
    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_work.IMG_URL
     *
     * @return the value of tt_work.IMG_URL
     *
     * @mbg.generated
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_work.IMG_URL
     *
     * @param imgUrl the value for tt_work.IMG_URL
     *
     * @mbg.generated
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_work.DESIGNER_ID
     *
     * @return the value of tt_work.DESIGNER_ID
     *
     * @mbg.generated
     */
    public Long getDesignerId() {
        return designerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_work.DESIGNER_ID
     *
     * @param designerId the value for tt_work.DESIGNER_ID
     *
     * @mbg.generated
     */
    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_work.IS_COVER
     *
     * @return the value of tt_work.IS_COVER
     *
     * @mbg.generated
     */
    public Boolean getIsCover() {
        return isCover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_work.IS_COVER
     *
     * @param isCover the value for tt_work.IS_COVER
     *
     * @mbg.generated
     */
    public void setIsCover(Boolean isCover) {
        this.isCover = isCover;
    }
}