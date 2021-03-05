package com.sapient.digital.weather.models;

import java.time.LocalDate;

public class DayForecastDetailsVO {
	
	private LocalDate date;
	private String minTemp;
	private String maxTemp;
	private String daytip;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}
	public String getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}
	public String getDaytip() {
		return daytip;
	}
	public void setDaytip(String daytip) {
		this.daytip = daytip;
	}
}
