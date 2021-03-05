package com.sapient.digital.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDM {
	
	private String main;

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

}
