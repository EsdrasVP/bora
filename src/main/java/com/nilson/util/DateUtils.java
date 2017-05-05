package com.nilson.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtils {

	private static final Logger LOGGER = LogManager.getLogger(DateUtils.class);
	private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat SDF_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final SimpleDateFormat SDF_TIME_BR = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	private static final SimpleDateFormat SDF_HOURS_MINUTES = new SimpleDateFormat("HH:mm");
	
	public DateUtils() {
	}

	public long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	public static String formatDate(Date date) {
		return format(SDF_DATE, date);
	}
	
	public static Date parseDate(String str) {
		return parse(SDF_DATE, str);
	}
	
	public static String formatTimestamp(Date date) {
		return format(SDF_TIMESTAMP, date);
	}
	
	public static Date parseTimestamp(String str) {
		return parse(SDF_TIMESTAMP, str);
	}
	
	public static String formatTime(Date date) {
		return format(SDF_TIME, date);
	}
	
	public static String formatBRTime(Date date) {
		return format(SDF_TIME_BR, date);
	}
	
	public static String formatHoursAndMinuts(Date date) {
		return format(SDF_HOURS_MINUTES, date);
	}
	
	public static Date parseTime(String str) {
		return parse(SDF_TIME, str);
	}
	
	public static String format(SimpleDateFormat format, Date date) {
		return date == null ? null : format.format(date);
	}
	
	public static Date parse(SimpleDateFormat format, String str) {
		try {
			return (str == null || str.isEmpty()) ? null : format.parse(str);
		} catch (ParseException e) {
			LOGGER.warn("Couldn't parse Date from String", e);
			return null;
		}
	}
	
	public static int getDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}
	
	public static long getDateDiff(Date initTime, Date endTime, TimeUnit timeUnit) {
	    long diffInMillies = endTime.getTime() - initTime.getTime();
	    return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
