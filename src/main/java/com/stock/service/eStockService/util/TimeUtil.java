package com.stock.service.eStockService.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {
	public static Date convertingDate(String inputDate){
		SimpleDateFormat utcdate=new SimpleDateFormat("yyyy-MM-dd");
		utcdate.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date utc = null;
		try {
			utc = utcdate.parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return utc;
	}
}
