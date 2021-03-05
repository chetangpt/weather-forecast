package com.sapient.digital.weather.gateways.interfaces;

import java.io.IOException;

import com.sapient.digital.weather.exception.WeatherForecastException;
import com.sapient.digital.weather.models.OpenWeatherMapDM;

public interface IOpenWeatherMapGatewayService {

	public OpenWeatherMapDM getOpenWeatherMapData(String cityName)throws WeatherForecastException,IOException ;
}
