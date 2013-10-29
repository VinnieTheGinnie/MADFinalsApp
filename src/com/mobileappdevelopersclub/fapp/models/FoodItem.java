package com.mobileappdevelopersclub.fapp.models;

public class FoodItem {
	
	private String resteraunt;
	private String description;
	
	public FoodItem(String resteraunt, String description) {
		super();
		this.resteraunt = resteraunt;
		this.description = description;
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
	
	
	
	
}
