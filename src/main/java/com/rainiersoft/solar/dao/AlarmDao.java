package com.rainiersoft.solar.dao;



import java.util.List;

import com.rainiersoft.solar.entity.AlarmEntity;
import com.rainiersoft.solar.entity.AlarmObjectEntity;

public interface AlarmDao {
	
	public AlarmEntity getAlarmDetails();
	
	public AlarmObjectEntity  getAlarmObjectDetails();

}
