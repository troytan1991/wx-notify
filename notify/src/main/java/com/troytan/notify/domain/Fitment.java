package com.troytan.notify.domain;

public class Fitment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_fitment.FITMENT_ID
     *
     * @mbg.generated
     */
    private Integer fitmentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_fitment.COVER_URL
     *
     * @mbg.generated
     */
    private String coverUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_fitment.CLASSIFY
     *
     * @mbg.generated
     */
    private Short classify;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_fitment.LABEL
     *
     * @mbg.generated
     */
    private String label;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_fitment.FITMENT_ID
     *
     * @return the value of tt_fitment.FITMENT_ID
     *
     * @mbg.generated
     */
    public Integer getFitmentId() {
        return fitmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_fitment.FITMENT_ID
     *
     * @param fitmentId the value for tt_fitment.FITMENT_ID
     *
     * @mbg.generated
     */
    public void setFitmentId(Integer fitmentId) {
        this.fitmentId = fitmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_fitment.COVER_URL
     *
     * @return the value of tt_fitment.COVER_URL
     *
     * @mbg.generated
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_fitment.COVER_URL
     *
     * @param coverUrl the value for tt_fitment.COVER_URL
     *
     * @mbg.generated
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_fitment.CLASSIFY
     *
     * @return the value of tt_fitment.CLASSIFY
     *
     * @mbg.generated
     */
    public Short getClassify() {
        return classify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_fitment.CLASSIFY
     *
     * @param classify the value for tt_fitment.CLASSIFY
     *
     * @mbg.generated
     */
    public void setClassify(Short classify) {
        this.classify = classify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_fitment.LABEL
     *
     * @return the value of tt_fitment.LABEL
     *
     * @mbg.generated
     */
    public String getLabel() {
        return label;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_fitment.LABEL
     *
     * @param label the value for tt_fitment.LABEL
     *
     * @mbg.generated
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }
}