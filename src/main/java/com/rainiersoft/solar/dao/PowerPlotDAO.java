package com.rainiersoft.solar.dao;

import java.util.List;

import com.rainiersoft.solar.entity.CurrentPower;
import com.rainiersoft.solar.entity.EnergyPower;

public interface PowerPlotDAO 
{
	public List<CurrentPower> getCurrentEnergy();

}
