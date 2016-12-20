package com.androidapp.bahs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.androidapp.bahs.service.AppContext;
import com.androidapp.bahs.service.utils.LogUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CommonUtility {

	public static void noTitleBar(FragmentActivity context) {
		context.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//		   context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager  = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public static String printKeyHash(FragmentActivity context) {
		PackageInfo packageInfo;
		String key = null;
		try {

			//getting application package name, as defined in manifest
			String packageName = context.getApplicationContext().getPackageName();

			//Retriving package info
			packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);

			LogUtils.error("Package Name=", context.getApplicationContext().getPackageName());

			for (Signature signature : packageInfo.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				key = new String(Base64.encode(md.digest(), 0));
				// String key = new String(Base64.encodeBytes(md.digest()));
				LogUtils.error("Key Hash=", key);
				//log.d generate valid hash key
				LogUtils.debug("Key D Hash= ", key);
				LogUtils.info("Key Hash="+key);
			}
		} catch (NameNotFoundException e1) {
			LogUtils.error("Name not found", e1.toString());
		}

		catch (NoSuchAlgorithmException e) {
			LogUtils.error("No such an algorithm", e.toString());
		} catch (Exception e) {
			LogUtils.error("Exception", e.toString());
		}

		return key;
	}

	public static void hideSoftKeyboard(FragmentActivity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		if (inputMethodManager!=null) {
			inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
		}
	}

	public static void hideSoftKeyboard(Activity activity) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    if(inputMethodManager!=null)
	    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}


	public static void checkGCM(FragmentActivity mContext) {
		// Make sure the device has the proper dependencies.
		// GCMRegistrar.checkDevice(mContext);
		// Make sure the manifest was properly set - comment out this line
		// while developing the app, then uncomment it when it's ready.
		//GCMRegistrar.checkManifest(mContext);
	}


	//Convert dp to pixel:
	public static int dpToPx(float dp) {
		DisplayMetrics displayMetrics = AppContext.getInstance().getContext().getResources().getDisplayMetrics();
		int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
		return px;
	}

	//	Convert pixel to dp:
	public static int pxToDp(float px) {
		DisplayMetrics displayMetrics = AppContext.getInstance().getContext().getResources().getDisplayMetrics();
		int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
		return dp;
	}


	public static int stringToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}



	public static void hideSoftKeyUI(View view, final Activity context) {
		if (!(view instanceof EditText)) {
			view.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (context != null) {
						hideSoftKeyboard(context);
					}
					return false;
				}

			});
		}

		// If a layout container, iterate over children and seed recursion.
		if (view instanceof ViewGroup) {
			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
				View innerView = ((ViewGroup) view).getChildAt(i);
				hideSoftKeyUI(innerView, context);
			}
		}
	}


}
