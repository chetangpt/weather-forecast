package com.sapient.digital.weather.services.implementation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.sapient.digital.weather.exception.WeatherForecastException;
import com.sapient.digital.weather.gateways.interfaces.IOpenWeatherMapGatewayService;
import com.sapient.digital.weather.models.DayForecastDetailsVO;
import com.sapient.digital.weather.models.ForecastVO;
import com.sapient.digital.weather.models.OpenWeatherMapDM;
import com.sapient.digital.weather.models.WeatherDataDM;
import com.sapient.digital.weather.services.interfaces.IWeatherForecastService;

public class WeatherForecastService implements IWeatherForecastService {

	@Inject
	IOpenWeatherMapGatewayService openWeatherMapGatewayService;
	
	@Override
	public ForecastVO getWeatherForecast(String cityName) throws WeatherForecastException, IOException {
		if(cityName==null ||cityName.isEmpty()) {
			throw new WeatherForecastException("400","cityname cannot be blank or null");
		}		
		OpenWeatherMapDM openWeatherMapDM = openWeatherMapGatewayService.
				getOpenWeatherMapData(cityName);
		
		return prepareForecast(openWeatherMapDM);
	}

	private ForecastVO prepareForecast(OpenWeatherMapDM openWeatherMapDM) {
		ForecastVO forecastVO = new ForecastVO();
		forecastVO.setCityId(openWeatherMapDM.getCity().getId());
		forecastVO.setCityName(openWeatherMapDM.getCity().getName());
		forecastVO.setCountry(openWeatherMapDM.getCity().getCountry());
		
		LocalDate currentDate = LocalDate.now();
		//We need to forecast only for next 3 days
		LocalDate forecastedDate = currentDate.plusDays(4);
		Map<LocalDate,List<Float>> dateToTemperatureMap = new HashMap<>();
		Map<LocalDate,String> dateToRainMap = new HashMap<>();
		for(WeatherDataDM weatherdetails : openWeatherMapDM.getList()) {
			if(weatherdetails.getDt_txt().isAfter(currentDate) && 
					weatherdetails.getDt_txt().isBefore(forecastedDate)) {
				LocalDate date = weatherdetails.getDt_txt();
				List<Float> tempList;
				if(dateToTemperatureMap.containsKey(date)){
					tempList = dateToTemperatureMap.get(date);
					tempList.add(weatherdetails.getMain().getTemp());
					dateToTemperatureMap.put(date,tempList);
				}else {
					tempList = new ArrayList<>();
					tempList.add(weatherdetails.getMain().getTemp());
					dateToTemperatureMap.put(date, tempList);
				}
				
				if(weatherdetails.getWeather().get(0).getMain().equals("Rain")) {
					dateToRainMap.put(date, "Rain");
				}
			}	
		}
		
		List<DayForecastDetailsVO> dayForecastDetailVOList = new ArrayList<>();
		for(Map.Entry<LocalDate,List<Float>> temperatureEntryData:dateToTemperatureMap.entrySet()) {
			DayForecastDetailsVO dayForecastDetailsVO = new DayForecastDetailsVO();
			LocalDate date = temperatureEntryData.getKey();
			dayForecastDetailsVO.setDate(date);
			
			List<Float> tempList = temperatureEntryData.getValue();
			Collections.sort(tempList);
			dayForecastDetailsVO.setMinTemp(tempList.get(0).toString());
			dayForecastDetailsVO.setMaxTemp(tempList.get(tempList.size()-1).toString());
			if(dateToRainMap.get(date)!=null) {
				dayForecastDetailsVO.setDaytip("Carry umbrella");	
			}else if(tempList.get(tempList.size()-1)>40) {
				dayForecastDetailsVO.setDaytip("Use sunscreen lotion");
			}else {
				dayForecastDetailsVO.setDaytip("Have a nice day");
			}
			dayForecastDetailVOList.add(dayForecastDetailsVO);
		}
		
		forecastVO.setForecast(dayForecastDetailVOList);
		return forecastVO;
	}
}
