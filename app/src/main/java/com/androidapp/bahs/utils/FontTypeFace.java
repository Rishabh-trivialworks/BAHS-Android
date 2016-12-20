package com.androidapp.bahs.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class FontTypeFace {
	
	public static void setTypeFace(Context context, View... view) {
		Typeface tf = Typeface.createFromAsset(context.getAssets(), "HelveticaNeueLT-Light.ttf");
		for (View v : view) {
			if(v instanceof TextView) {
				((TextView)v).setTypeface(tf);
			} else if(v instanceof EditText) {
				((EditText)v).setTypeface(tf);
			} else if(v instanceof Button) {
				((Button)v).setTypeface(tf);
			} else if(v instanceof CheckBox) {
				((CheckBox)v).setTypeface(tf);
			} else if(v instanceof RadioButton) {
				((RadioButton)v).setTypeface(tf);
			}
		}
	}
	public static void setTypeFaceBold(Context context, View... view) {
		Typeface tf = Typeface.createFromAsset(context.getAssets(), "HelveticaNeueLT-Light.ttf");
		for (View v : view) {
			if(v instanceof TextView) {
				((TextView)v).setTypeface(tf, Typeface.BOLD);
			} else if(v instanceof EditText) {
				((EditText)v).setTypeface(tf, Typeface.BOLD);
			} else if(v instanceof Button) {
				((Button)v).setTypeface(tf, Typeface.BOLD);
			} else if(v instanceof CheckBox) {
				((CheckBox)v).setTypeface(tf, Typeface.BOLD);
			} else if(v instanceof RadioButton) {
				((RadioButton)v).setTypeface(tf, Typeface.BOLD);
			}
		}
	}
}
