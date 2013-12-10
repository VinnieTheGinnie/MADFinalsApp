package com.mobileappdevelopersclub.fapp.models;

public class BusRoute {
	
	private String title;
	
	private String pdfUrl;

	public BusRoute(String title, String pdfUrl) {
		super();
		this.title = title;
		this.pdfUrl = pdfUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}
}
