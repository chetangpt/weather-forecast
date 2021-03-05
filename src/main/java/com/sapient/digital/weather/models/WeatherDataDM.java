package com.sapient.digital.weather.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDataDM {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate dt_txt;
	
	private MainDM main;
	private List<WeatherDM> weather = new ArrayList<>();
	public LocalDate getDt_txt() {
		return dt_txt;
	}
	public void setDt_txt(LocalDate dt_txt) {
		this.dt_txt = dt_txt;
	}
	public MainDM getMain() {
		return main;
	}
	public void setMain(MainDM main) {
		this.main = main;
	}
	public List<WeatherDM> getWeather() {
		return weather;
	}
	public void setWeather(List<WeatherDM> weather) {
		this.weather = weather;
	}
	
	
}
