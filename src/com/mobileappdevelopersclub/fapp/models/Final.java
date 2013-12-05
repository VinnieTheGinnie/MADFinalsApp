package com.mobileappdevelopersclub.fapp.models;

public class Final extends ScheduleItem {
	
	//Sample JSON array of finals 
	//[{"section":"0101","day":"Fri, Dec 20","time":"1:30 pm - 3:30 pm","location":"CSI 1122","instructor":"Samrat Bhattacharjee"},{"section":"0201","day":"Thu, Dec 19","time":"10:30 am - 12:30 pm","location":"CSI 2107","instructor":"Ashok Agrawala"}]

	
	private static final long serialVersionUID = 1L;
	
//	@SerializedName("section")
//	private String section;
//	
//	@SerializedName("day")
//	private String day;
//	
//	@SerializedName("time")
//	private String time;
//	
//	@SerializedName("location")
//	private String location;
//	
//	@SerializedName("instructor")
//	private String instructor;
	
	public Final(){
		//stub
	}

	public Final(String title, String time, String day, String location,
			String instructor, String section) {
		super(title, time, day, location, instructor, section);
	}
}
