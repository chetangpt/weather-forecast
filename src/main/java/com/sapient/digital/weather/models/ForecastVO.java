package com.sapient.digital.weather.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ForecastVO extends GenericResponseVO {
	
	private List<DayForecastDetailsVO> forecast = new ArrayList<>();
	private String cityId;
	private String cityName;
	private String country;

	public List<DayForecastDetailsVO> getForecast() {
		return forecast;
	}

	public void setForecast(List<DayForecastDetailsVO> forecast) {
		this.forecast = forecast;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
