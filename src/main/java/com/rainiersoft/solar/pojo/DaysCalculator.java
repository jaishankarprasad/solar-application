package com.rainiersoft.solar.pojo;

import java.util.Calendar;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DaysCalculator
{
	/*public static void main(String args[])
	{
		String startDate = "24-Oct-2018 11:43:00";
		String endDate = "26-Oct-2018 21:03:00";
		String curDate = "2018-12-29 12";
		DaysCalculator daysCalculator = new DaysCalculator();

				int days = daysCalculator.getDifferenceBetweenDates(startDate,endDate);
		System.out.println("Number of Days between dates: "+days);

		String date1 = daysCalculator.getNextDate(curDate);
		System.out.println("Next Day is: "+date1);

		//daysCalculator.getDifferenceBetweenDatesH(startDate, endDate);

		daysCalculator.getNextDateH(curDate);
		daysCalculator.getNextDate(curDate);
	}*/
	public int getDifferenceBetweenDates(String dateBeforeString,String dateAfterString)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		int daysBetween = 0;
		try 
		{
			Date dateBefore = formatter.parse(dateBeforeString);
			Date dateAfter = formatter.parse(dateAfterString);
			long difference = dateAfter.getTime() - dateBefore.getTime();
			daysBetween = (int) (difference / (1000*60*60*24));


		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return daysBetween+1;
	}


	public long getDifferenceBetweenDatesH(String dateBeforeString,String dateAfterString)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH");
		long diffHours = 0;
		Date d2 = null;
		Date d1 = null;
		try
		{
			d1 = formatter.parse(dateBeforeString);
			d2 = formatter.parse(dateAfterString);

		}
		catch(ParseException he)
		{
			he.printStackTrace();
		}

		//in milliseconds
		long diff = d2.getTime() - d1.getTime();

		diffHours = diff / (60 * 60 * 1000);


		System.out.print(diffHours + " hours");

		return diffHours;
	}
	public String getNextDate(String  curDate) {
		final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try 
		{
			date = format.parse(curDate);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		System.out.println(format.format(calendar.getTime()));
		return format.format(calendar.getTime()); 
	}
	public String getNextDateH(Date curDate) 
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");

		Date date = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.add(Calendar.HOUR, 1);
		String afterOneHour = format.format(calendar.getTime());
		System.out.println("AFTER ONE HOUR::"+afterOneHour);
		return afterOneHour;
	}

}