package com.mobileappdevelopersclub.fapp.models;

import java.util.ArrayList;

public class Library {

	private String name;
	private String location;
	private String hourOpen;
	private String hourClose;
	private ArrayList<String> studyAreas;
	
	public Library(String name, String location, String hourOpen,
			String hourClose, ArrayList<String> studyAreas) {
		super();
		this.name = name;
		this.location = location;
		this.hourOpen = hourOpen;
		this.hourClose = hourClose;
		this.studyAreas = studyAreas;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public ArrayList<String> getStudyAreas() {
		return studyAreas;
	}
	public void setStudyAreas(ArrayList<String> studyAreas) {
		this.studyAreas = studyAreas;
	}
	
	
}
