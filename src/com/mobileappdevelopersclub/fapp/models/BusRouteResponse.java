package com.mobileappdevelopersclub.fapp.models;

import java.util.List;

public class BusRouteResponse {
	
	
	private List<BusRoute> busRoutes;

	public BusRouteResponse(List<BusRoute> busRoutes) {
		super();
		this.busRoutes = busRoutes;
	}

	public List<BusRoute> getBusRoutes() {
		return busRoutes;
	}

	public void setBusRoutes(List<BusRoute> busRoutes) {
		this.busRoutes = busRoutes;
	}
	
}
