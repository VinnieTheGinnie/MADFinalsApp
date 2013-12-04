package com.mobileappdevelopersclub.fapp.models;

public class Hours {

	
	private String hourOpen;
	private String hourClose;
	
	public Hours(String hourOpen, String hourClose) {
		super();
		this.hourOpen = hourOpen;
		this.hourClose = hourClose;
	}
	public String getHourOpen() {
		return hourOpen;
	}
	public void setHourOpen(String hourOpen) {
		this.hourOpen = hourOpen;
	}
	public String getHourClose() {
		return hourClose;
	}
	public void setHourClose(String hourClose) {
		this.hourClose = hourClose;
	}
}
