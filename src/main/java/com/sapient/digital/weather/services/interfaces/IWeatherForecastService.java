package com.sapient.digital.weather.services.interfaces;

import java.io.IOException;

import com.sapient.digital.weather.exception.WeatherForecastException;
import com.sapient.digital.weather.models.ForecastVO;

public interface IWeatherForecastService {

	public ForecastVO getWeatherForecast(String cityName) throws WeatherForecastException, IOException;
}
