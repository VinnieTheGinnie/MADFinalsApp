package com.mobileappdevelopersclub.fapp.models;

import java.util.List;

public class LibraryResponse {

	
	private List<Library> libraries;

	public LibraryResponse(List<Library> libraries) {
		super();
		this.libraries = libraries;
	}

	public List<Library> getLibraries() {
		return libraries;
	}

	public void setLibraries(List<Library> libraries) {
		this.libraries = libraries;
	}
	
}
