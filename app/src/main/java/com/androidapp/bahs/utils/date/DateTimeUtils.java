package com.androidapp.bahs.utils.date;

import com.androidapp.bahs.utils.device.Syso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mobikasa on 12/20/2016.
 */

public class DateTimeUtils {



    public void getCurrentDate(){
        DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date=new Date();
        Syso.debug("Current_Date---->"+dateFormat.format(date));
        Syso.debug("Current_Date---->"+date);
        Syso.debug("Current_Date---->"+date.toString());
    }

    /*
    * This methods used to get diffence between two dates.
    * This method accept the string then we are converting in MM/dd/yyyy
    * */
    public int dayDifferenceInTwoDates(Date startDate, Date endDate){
        int diffInDays=0;

        try {
            int duration  =(int) (startDate.getTime() - endDate.getTime());
            diffInDays = Math.abs(duration / (1000 * 60 * 60 * 24));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffInDays;

    }

     /*
    * This methods used to get diffence between two dates.
    * We can pass dateformat in this method but string date should be same as format.
    * */

    public int dayDifferenceInTwoDates(String startDate, String endDate,DateFormat dateFormat){
        int diffInDays=0;
        try {
            Date date1= dateFormat.parse(startDate);
            Date date2= dateFormat.parse(endDate);
            int duration  =(int) (date1.getTime() - date2.getTime());
            diffInDays = Math.abs(duration / (1000 * 60 * 60 * 24));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffInDays;

    }



    public void dayDifferenceWithCurrentDate(){

    }



}
