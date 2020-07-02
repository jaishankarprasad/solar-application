package com.rainiersoft.solar.entity;

import java.util.List;

public class AlarmEntity {
	String eventDateAndTime;
	// String evetId;
	String eventSourceName;
	String alarmMessage;
	int alarmStatus;
	public String getEventDateAndTime() {
		return eventDateAndTime;
	}
	public List<AlarmObjectEntity> setEventDateAndTime(String eventDateAndTime) {
		this.eventDateAndTime = eventDateAndTime;
		return null;
	}
	
	public String getEventSourceName() {
		return eventSourceName;
	}
	public void setEventSourceName(String eventSourceName) {
		this.eventSourceName = eventSourceName;
	}
	public String getAlarmMessage() {
		return alarmMessage;
	}
	public List<AlarmObjectEntity> setAlarmMessage(String alarmMessage) {
		this.alarmMessage = alarmMessage;
		return null;
	}
	public int getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(int alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	
	
	

}
