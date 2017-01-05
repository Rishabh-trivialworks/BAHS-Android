package com.androidapp.bahs.utils.device;

import android.util.Log;

/**
 * @author This class is used for adding log in-line. 
 * Need to change "mIsDebug" value "false" when deploy.
 *
 */
public class LogUtils {
	
	private static boolean mIsDebug = true;
	
	public static void info(Object object){
		if(mIsDebug){
			Log.i("LoggerUtility", "" + object);
		}
	}
	
	public static void info(String tag,Object object){
		if(mIsDebug){
			Log.i(tag, "" + object);
		}
	}
	
	public static void debug(Object object){
		if(mIsDebug){
			Log.d("LoggerUtility", "" + object);
		}
	}
	
	public static void debug(String tag,Object object){
		if(mIsDebug){
			Log.d(tag, "" + object);
		}
	}
	
	public static void error(Object object){
		if(mIsDebug){
			Log.e("LoggerUtility", "" + object);
		}
	}
	
	public static void error(String tag,Object object){
		if(mIsDebug){
			Log.e(tag, "" + object);
		}
	}

}
