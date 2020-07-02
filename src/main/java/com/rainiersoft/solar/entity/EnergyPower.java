package com.rainiersoft.solar.entity;

public class EnergyPower {

	String netExpEnergy;	
	String netImpEnergy;
	String power;
	String perfomanceRatio;
	String dateAndTime;

	public String getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public String getNetExpEnergy() {
		return netExpEnergy;
	}
	public void setNetExpEnergy(String netExpEnergy) {
		this.netExpEnergy = netExpEnergy;
	}
	public String getNetImpEnergy() {
		return netImpEnergy;
	}
	public void setNetImpEnergy(String netImpEnergy) {
		this.netImpEnergy = netImpEnergy;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getPerfomanceRatio() {
		return perfomanceRatio;
	}
	public void setPerfomanceRatio(String perfomanceRatio) {
		this.perfomanceRatio = perfomanceRatio;
	}

}
