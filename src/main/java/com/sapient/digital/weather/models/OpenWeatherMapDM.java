package com.sapient.digital.weather.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMapDM {

	private String cod;
	private int cnt;
	private List<WeatherDataDM> list = new ArrayList<>();
	private CityDM city;
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public List<WeatherDataDM> getList() {
		return list;
	}
	public void setList(List<WeatherDataDM> list) {
		this.list = list;
	}
	public CityDM getCity() {
		return city;
	}
	public void setCity(CityDM city) {
		this.city = city;
	}
}
