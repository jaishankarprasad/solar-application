package com.rainiersoft.solar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rainiersoft.solar.dao.YearMonthDayWiseDAO;
import com.rainiersoft.solar.entity.DaysFetcher;
import com.rainiersoft.solar.entity.YearMonthDayWise;
import com.rainiersoft.solar.entity.YearWiseDayPower;

@RestController
@RequestMapping("/rest")
public class YearMonthDayWiseController 
{
	@Autowired
	YearMonthDayWiseDAO yearMonthDayWiseDAO;

	@RequestMapping("/getYearWiseList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<YearMonthDayWise> getYearWiseList()
	{
		List<YearMonthDayWise> yearMonthDayWises = new ArrayList<>();
		yearMonthDayWises = yearMonthDayWiseDAO.getYearWiseList();
		return yearMonthDayWises;
	}
	
	@RequestMapping("/getListByYearForMonths/{year}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<YearMonthDayWise> getListByYearForMonths(@PathVariable(value = "year") String year)
	{
		List<YearMonthDayWise> yearMonthDayWises = new ArrayList<>();
		yearMonthDayWises = yearMonthDayWiseDAO.getListByYearForMonths(year);
		return yearMonthDayWises;
	}
	
	@RequestMapping("/getListByMonthsForDays/{year}/{month}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<YearMonthDayWise> getListByMonthsForDays(@PathVariable(value="year") String year ,@PathVariable(value="month") String month)
	{
		DaysFetcher daysFetcher = new DaysFetcher();
		daysFetcher.setMonth(month);
		daysFetcher.setYear(year);
		List<YearMonthDayWise> yearMonthDayWises = new ArrayList<>();
		yearMonthDayWises = yearMonthDayWiseDAO.getListByMonthsForDays(daysFetcher);
		return yearMonthDayWises;
	}
	
	// year day wise power ...
	
	@RequestMapping("/PowerBasedOnDate/{date}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<YearWiseDayPower> getListOfPowerYearDayWise(@PathVariable(value="date")String date){
		List<YearWiseDayPower> powerYearDayWise= new ArrayList<>();
		powerYearDayWise=yearMonthDayWiseDAO.getYearWiseDayPower(date);
		return powerYearDayWise;
		
	}

}
