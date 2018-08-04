package com.troytan.notify.dto;

import java.util.List;

import com.troytan.notify.domain.Work;

public class DesignerDto {
    
    private Long               designerId;
    private String             name; 
    private String             photo;
    private int                orderNum;   
    private String             photoDetail;
    private List<Work>         workList;
	public Long getDesignerId() {
		return designerId;
	}
	public void setDesignerId(Long designerId) {
		this.designerId = designerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getPhotoDetail() {
		return photoDetail;
	}
	public void setPhotoDetail(String photoDetail) {
		this.photoDetail = photoDetail;
	}
	public List<Work> getWorkList() {
		return workList;
	}
	public void setWorkList(List<Work> workList) {
		this.workList = workList;
	}
	

}
