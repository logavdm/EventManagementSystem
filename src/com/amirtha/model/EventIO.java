package com.amirtha.model;

import java.util.Date;

public class EventIO {

	private String name;
	private String venue;
	private Date fromDate;
	private Date toDate;
	private String description;
	

	public EventIO(String name, String venue, Date fromDate, Date toDate, String description) {
		this.name = name;
		this.venue = venue;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.description = description;
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
