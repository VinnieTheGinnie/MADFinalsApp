package com.mobileappdevelopersclub.fapp.models;

import java.util.ArrayList;

public class Tweet {

	private String username;
	private String text;
	private String miniImage;
	private ArrayList<String> hashTags;
	
	
	public Tweet(String username, String text, String miniImage) {
		super();
		this.username = username;
		this.text = text;
		this.miniImage= miniImage;
		//this.hashTags = hashTags;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
	}
	public String getMiniImage(){
		return miniImage;
	}
	public void setMiniImage(String miniImage){
		this.miniImage = miniImage;
	}
	public ArrayList<String> getHashTags(){
		return hashTags;
	}
	public void setHashTags (ArrayList<String> hashTags){
		this.hashTags = hashTags;
	}
	
	
}
