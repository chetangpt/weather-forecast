package com.sapient.digital.weather.exception;

public class WeatherForecastException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private final String errCode;
	private final String errMsg;
	private static final String exceptionErrorCode ="9999";
	
	public WeatherForecastException(String errCode,String errMsg) {
		 super(errMsg);
		 this.errCode =errCode;
		 this.errMsg= errMsg;
	}
	
	public WeatherForecastException(Exception e) {
		super(e);
		this.errMsg = e.getMessage();
		this.errCode = exceptionErrorCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}
	
}
