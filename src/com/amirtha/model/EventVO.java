package com.amirtha.model;

import java.util.Date;

public class EventVO {

	private Long id;
	private String name;
	private String venue;
	private String date;
	private String time;
	private String description;
	private Date createTime;
	private Date updatedTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getFromDate() {
		return date;
	}
	public void setFromDate(String fromDate) {
		this.date = fromDate;
	}
	public String getToDate() {
		return time;
	}
	public void setToDate(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}	
}
