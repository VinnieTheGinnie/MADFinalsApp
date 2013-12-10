package com.mobileappdevelopersclub.fapp.models;

import java.util.List;

public class HealtResourcesResponse {

	private List<HealthResource> healthResources;

	public HealtResourcesResponse(List<HealthResource> healthResources) {
		super();
		this.healthResources = healthResources;
	}

	public List<HealthResource> getHealthResources() {
		return healthResources;
	}

	public void setHealthResources(List<HealthResource> healthResources) {
		this.healthResources = healthResources;
	}
	
	

}
