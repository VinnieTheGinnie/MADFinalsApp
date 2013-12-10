package com.mobileappdevelopersclub.fapp.models;

public class FoodItem {
	
	private String resteraunt;
	private String description;
	private String photo;
	
	public FoodItem(String resteraunt, String description, String photo) {
		super();
		this.resteraunt = resteraunt;
		this.description = description;
		this.photo = photo;
	}
	
	public String getResteraunt() {
		return resteraunt;
	}
	public void setResteraunt(String resteraunt) {
		this.resteraunt = resteraunt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
