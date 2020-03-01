package com.corona.coronavirustracker.models;

public class LocationStat {
	
	private String state;
	private String country;
	private int totalCases;
	private int changesSinceLastDay;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public int getChangesSinceLastDay() {
		return changesSinceLastDay;
	}
	public void setChangesSinceLastDay(int changesSinceLastDay) {
		this.changesSinceLastDay = changesSinceLastDay;
	}
	
	@Override
	public String toString() {
		return "LocationStat [state=" + state + ", country=" + country + ", totalCases=" + totalCases + "]";
	}
	
}
