package com.sapient.digital.weather.gateways.implementation;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sapient.digital.weather.exception.WeatherForecastException;
import com.sapient.digital.weather.gateways.interfaces.IOpenWeatherMapGatewayService;
import com.sapient.digital.weather.models.OpenWeatherMapDM;

public class OpenWeatherMapGatewayService implements IOpenWeatherMapGatewayService {

	@Inject
	RestTemplate restTemplate;
	
	@Override
	public OpenWeatherMapDM getOpenWeatherMapData(String cityName) throws WeatherForecastException,IOException {
		
		final String uri = "http://api.openweathermap.org/data/2.5/forecast";
		String result = "";
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
		        .queryParam("q", cityName)
		        .queryParam("units", "metric")
		        .queryParam("appid", "99b76e12fa5ac19587789cd72b251c84");
	 
		ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			result = response.getBody();
		}else {
			throw new WeatherForecastException("502", "Gateway service call to OpenWeatherMap failed or no data found");
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		OpenWeatherMapDM openWeatherMapDM = objectMapper.readValue(result, OpenWeatherMapDM.class);
		return openWeatherMapDM;
	}

}
