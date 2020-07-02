package com.rainiersoft.solar.entity;

import java.util.List;

public class AlarmObjectEntity {
	
	String message;
	boolean status;
	List<AlarmEntity> alarmObject;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<AlarmEntity> getAlarmObject() {
		return alarmObject;
	}
	public void setAlarmObject(List<AlarmEntity> alarmObject) {
		this.alarmObject = alarmObject;
	}
	
	
	
	
	

}
