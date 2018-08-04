package com.troytan.notify.dto;

import java.util.List;

import com.troytan.notify.domain.FitmentDetail;

public class FitmentDto {

	private Long fitmentId;
	private String coverUrl;
	private Short classify;
	private String label;
	private List<FitmentDetail> fitmentDetailsList;
	public Long getFitmentId() {
		return fitmentId;
	}
	public void setFitmentId(Long fitmentId) {
		this.fitmentId = fitmentId;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public Short getClassify() {
		return classify;
	}
	public void setClassify(Short classify) {
		this.classify = classify;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<FitmentDetail> getFitmentDetailsList() {
		return fitmentDetailsList;
	}
	public void setFitmentDetailsList(List<FitmentDetail> fitmentDetailsList) {
		this.fitmentDetailsList = fitmentDetailsList;
	}
	

}
