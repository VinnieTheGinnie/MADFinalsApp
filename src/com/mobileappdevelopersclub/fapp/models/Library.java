package com.mobileappdevelopersclub.fapp.models;

import java.util.ArrayList;

public class Library {

	private String name;
	private String photo;
	private ArrayList<String> studyAreas;
	private ArrayList<Hours> hours;
	
	public Library(String name, String photo, ArrayList<String> studyAreas,
			ArrayList<Hours> hours) {
		super();
		this.name = name;
		this.photo = photo;
		this.studyAreas = studyAreas;
		this.hours = hours;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getStudyAreas() {
		return studyAreas;
	}
	public void setStudyAreas(ArrayList<String> studyAreas) {
		this.studyAreas = studyAreas;
	}

	public ArrayList<Hours> getHours() {
		return hours;
	}

	public void setHours(ArrayList<Hours> hours) {
		this.hours = hours;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
