package com.androidapp.bahs.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import android.util.Log;

import com.androidapp.bahs.utils.device.LogUtils;

public class DateUtils {
	
	public final static String DATE_FORMAT_1 = "MMM dd, yyyy";
	public final static String DATE_FORMAT_2 = "MM/dd";
	public final static String DATE_FORMAT_3 = "MMM dd, yyyy hh:mm aa";
	public final static String DATE_FORMAT_4 = "MMMM dd, yyyy hh:mm aa";
	public final static String DATE_FORMAT_5 = "yyyy-MM-dd hh:mm:ss";
	public final static String DATE_FORMAT_6 = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_FORMAT_7 = "yyyy-MM-dd";
	public final static String DATE_FORMAT_8 = "MMMM dd";
	public final static String DATE_FORMAT_9 = "yyyy-MMM-dd";
	public final static String DATE_FORMAT_10 = "EEE, MMM dd";
	public final static String DATE_FORMAT_11 = "EEE, MMM dd, yyyy";
	public final static String DATE_FORMAT_12 = "MMM dd, hh:mm aa";
	public final static String DATE_FORMAT_13 = "MM/dd/yy";
	public final static String DATE_FORMAT_14 = "dd/MM/yyyy";
	public final static String TIME_FORMAT_1 = "hh:mm:ss";
	public final static String TIME_FORMAT_2 = "hh:mm aaa";
	public final static String TIME_FORMAT_3 = "hh:mm";
	public final static String TIME_FORMAT_4 = "HH:mm:ss";
	public final static String TIME_FORMAT_5 = "hh:mm aaaa";
	public final static String DATE_FORMAT_15 = "dd MMMM, yyyy";
	public final static String DATE_FORMAT_16 = "MMM dd";
	public final static String DATE_FORMAT_17 = "yyyy-MMM-dd HH:mm";
	public final static long MILLISECONDS_ONE_HOUR = 1000*60*60;
	public final static long MILLISECONDS_ONE_DAY = 1000*60*60*24;
	
	public static String convertDateTime(long milliseconds, String outPutFormat){
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milliseconds);
		
		SimpleDateFormat sdf = new SimpleDateFormat(outPutFormat, Locale.getDefault());	
		String outPutDate = sdf.format(cal.getTime());
		
		return outPutDate;
	}
	
	
	public static long getTimeInMillis(String dateTime, String inputFormat){
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(inputFormat, Locale.getDefault());
			Date date = sdf.parse(dateTime);
			return date.getTime();
		} catch (ParseException e) {
			LogUtils.error(e.getMessage());
			return 0;
		}
	}
	
	public static String getBestDay(long inputTimeStamp, String outPutFormat){
		
		Calendar calInput = Calendar.getInstance();
		calInput.setTime(new Date(inputTimeStamp));
	    
	    Calendar calDevice = Calendar.getInstance();
	    calDevice.setTimeInMillis(System.currentTimeMillis());
	    
	    int monthInput = calInput.get(Calendar.MONTH);
	    int monthDevice = calDevice.get(Calendar.MONTH);
	    
	    int dayInput = calInput.get(Calendar.DAY_OF_MONTH);
	    int dayDevice = calDevice.get(Calendar.DAY_OF_MONTH);
	    
	    String outPutDay = "";
	    
	    if(monthInput == monthDevice && dayInput == dayDevice){
	    	outPutDay = "Today";
	    	return outPutDay;
	    }
	    
	    else if(monthInput == monthDevice && dayInput+1 == dayDevice){
	    	outPutDay = "Yesterday";
	    	return outPutDay;
	    }
	    else{
	    	SimpleDateFormat sdf = new SimpleDateFormat(outPutFormat, Locale.getDefault());	
			outPutDay = sdf.format(calInput.getTime());
			return outPutDay;
	    }
	}
	
	public static String getCurrentDateTime(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_6,Locale.getDefault());
		String strDate = sdf.format(c.getTime());
		LogUtils.debug("DateUtils", "CurrentDateTime = "+strDate);
		return strDate;
	}
	
	public static int getDaysDifference(long startTimeStamp, long endTimeStamp){
		
		Date startDate = new Date(startTimeStamp);
		Date endDate = new Date(endTimeStamp);
		
		LogUtils.debug("startDate = ", startDate);
		LogUtils.debug("endDate = ", endDate);
		
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		
		startCal.setTimeInMillis(startTimeStamp);
		endCal.setTimeInMillis(endTimeStamp);
		
		int diff = endCal.get(Calendar.DAY_OF_YEAR) - startCal.get(Calendar.DAY_OF_YEAR);
	    diff = diff + (startCal.getMaximum(Calendar.DAY_OF_YEAR) * (endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR)));

	    return diff;
	}
	
	public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate){

	    Date parsed = null;
	    String outputDate = "";

	    SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.getDefault());
	    SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.getDefault());

	    try {
	        parsed = df_input.parse(inputDate);
	        outputDate = df_output.format(parsed);

	    } catch (ParseException e) { 
	    	LogUtils.debug("DateUtils", "ParseException - dateFormat");
	    }

	    return outputDate;

	}
	
	public static String getDateDifferences(String currentDate, String serverDate){
		String dateDiff = "";
		Date d1 = null;
		Date d2 = null;
		
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_6,Locale.getDefault());
 
		try {
			d1 = format.parse(currentDate);
			d2 = format.parse(serverDate);
 
			//in milliseconds
			long diff = d1.getTime() - d2.getTime();
			long diffDays = diff / MILLISECONDS_ONE_DAY;
			
			if(diffDays <= 0){
				System.out.println("Days are equal...");
				dateDiff = getTimeinAMPM(serverDate);
			} else if(diffDays == 1){
				dateDiff = "Yesterday";
				System.out.println("Yesterday...");	
			}else {
				dateDiff = formateDateFromstring(DATE_FORMAT_7, DATE_FORMAT_1, serverDate);
				System.out.println("date...");	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dateDiff;
	}
	
	public static String  getTimeinAMPM(String timeValue){
		String time = "";
		String str = getUTCDateTime(timeValue);
		String date = "", timeStr = "";
		StringTokenizer tk = new StringTokenizer(str);
		if(tk.hasMoreElements()){
			   date = tk.nextElement().toString();  
		       timeStr = tk.nextElement().toString();  
		}
		Log.e("DateUtils", date);
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_1, Locale.getDefault());
        SimpleDateFormat sdfs = new SimpleDateFormat(TIME_FORMAT_2, Locale.getDefault());
        Date dt;
        try {    
            dt = sdf.parse(timeStr);
            time = sdfs.format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return time;
	}
	
	public static String getUTCDateTime(String currentTime) {
		String formattedDate = "";
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_6,Locale.getDefault());
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date;
		try {
			date = df.parse(currentTime);
			df.setTimeZone(TimeZone.getDefault());
			formattedDate = df.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return formattedDate;
	}	
	
	public static String getLocalTimeFromUTC(String currentTime) {
		String formattedDate = "";
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_6, Locale.getDefault());
		df.setTimeZone(TimeZone.getDefault());
		Date date;
		try {
			date = df.parse(currentTime);
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
			formattedDate = df.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return formattedDate;
	}
	
	public static String getLocalTimeFromUTC(String currentTime, String format) {
		String formattedDate = "";
		SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date;
		try {
			date = df.parse(currentTime);
			df.setTimeZone(TimeZone.getDefault());
			formattedDate = df.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return formattedDate;
	}
	
	public static String getUTCFromLocalTime(String localTime, String dateFormat) {
		String formattedDate = "";
		SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.getDefault());
		df.setTimeZone(TimeZone.getDefault());
		Date date;
		try {
			date = df.parse(localTime);
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
			formattedDate = df.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return formattedDate;
	}
}
