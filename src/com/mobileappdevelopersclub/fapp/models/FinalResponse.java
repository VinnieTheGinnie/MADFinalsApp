package com.mobileappdevelopersclub.fapp.models;

import java.util.List;

public class FinalResponse {

//	[{"section":"0101","day":"Fri, Dec 20","time":"1:30 pm - 3:30 pm","location":"CSI 1122","instructor":"Samrat Bhattacharjee"},{"section":"0201","day":"Thu, Dec 19","time":"10:30 am - 12:30 pm","location":"CSI 2107","instructor":"Ashok Agrawala"}]
	
	private List<Final> finals;

	public FinalResponse(List<Final> finalObject) {
		super();
		this.finals = finalObject;
	}

	public List<Final> getFinals() {
		return finals;
	}

	public void setFinals(List<Final> finalObject) {
		this.finals = finalObject;
	}
	
}
