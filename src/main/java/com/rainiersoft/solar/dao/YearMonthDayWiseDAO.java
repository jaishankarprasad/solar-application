package com.rainiersoft.solar.dao;

import java.util.List;
import java.util.Set;

import com.rainiersoft.solar.entity.DaysFetcher;
import com.rainiersoft.solar.entity.YearMonthDayWise;
import com.rainiersoft.solar.entity.YearWiseDayPower;

public interface YearMonthDayWiseDAO 
{
	public List<YearMonthDayWise> getYearWiseList();
	public List<YearMonthDayWise> getListByYearForMonths(String year);
	public List<YearMonthDayWise> getListByMonthsForDays(DaysFetcher daysFetcher);
	
	
	
	// Year Wise Day Power.
	
	public List<YearWiseDayPower> getYearWiseDayPower(String date);

}
