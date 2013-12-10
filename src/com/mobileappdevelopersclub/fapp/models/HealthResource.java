package com.mobileappdevelopersclub.fapp.models;

public class HealthResource {

	private String title;
	
	private String contact;
	
	private String description;

	public HealthResource(String title, String contact, String description) {
		super();
		this.title = title;
		this.contact = contact;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
