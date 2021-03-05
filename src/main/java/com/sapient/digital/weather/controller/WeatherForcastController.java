package com.sapient.digital.weather.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.digital.weather.exception.WeatherForecastException;
import com.sapient.digital.weather.models.ForecastVO;
import com.sapient.digital.weather.services.interfaces.IWeatherForecastService;


/**
 * @author Chetan Gupta
 */
@RestController
@RequestMapping("/weather/v1")
public class WeatherForcastController {
	
	@Inject
	IWeatherForecastService weatherForecastService; 
	
	@RequestMapping(
			value = "/forecast/{cityName}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ForecastVO> getforecastDetails(
		@PathVariable("cityName") String cityName){
		try {
			ForecastVO forecastVO =weatherForecastService.getWeatherForecast(cityName);
			forecastVO.setCode(HttpStatus.OK.value());
			forecastVO.setStatus(HttpStatus.OK.toString());
			forecastVO.setMessage("City forecasted data successfully");
			return new ResponseEntity<>(forecastVO, HttpStatus.OK);
		}catch(WeatherForecastException e) {
			ForecastVO forecastVO = new ForecastVO();
			forecastVO.setCode(Integer.parseInt(e.getErrCode()));
			forecastVO.setStatus("Failure");
			forecastVO.setMessage(e.getErrMsg());
			return new ResponseEntity<>(forecastVO,HttpStatus.resolve(Integer.parseInt(e.getErrCode())));
		}catch(Exception e) {
			ForecastVO forecastVO = new ForecastVO();
			forecastVO.setCode(500);
			forecastVO.setStatus("Failure");
			forecastVO.setMessage(e.getMessage());
			return new ResponseEntity<>(forecastVO,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
