package com.sapient.digital.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.sapient.digital.weather.gateways.implementation.OpenWeatherMapGatewayService;
import com.sapient.digital.weather.gateways.interfaces.IOpenWeatherMapGatewayService;
import com.sapient.digital.weather.services.implementation.WeatherForecastService;
import com.sapient.digital.weather.services.interfaces.IWeatherForecastService;

@Configuration
public class ResourcesConfig {

	@Bean
	public IWeatherForecastService weatherForecastService() {
		return new WeatherForecastService();
	}
	
	@Bean
	public IOpenWeatherMapGatewayService openWeatherMapGatewayService() {
		return new OpenWeatherMapGatewayService();
	}
	
	@Bean
	public RestTemplate restTemplate() {
	 
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	    factory.setConnectTimeout(3000);
	    factory.setReadTimeout(3000);
	    return new RestTemplate(factory);
	}
}
