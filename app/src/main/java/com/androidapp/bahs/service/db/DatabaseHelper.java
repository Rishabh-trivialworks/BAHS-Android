package com.androidapp.bahs.service.db;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidapp.bahs.service.AppContext;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static DatabaseHelper INSTANCE;
	
	public static DatabaseHelper getInstance(Application context){
		if(INSTANCE == null){
			INSTANCE = new DatabaseHelper(context);	
		}
		return INSTANCE;
	}

	public static SQLiteDatabase getDatabase(){
		getInstance(AppContext.getInstance().getContext());
		return INSTANCE.getWritableDatabase();
	}

	
	private DatabaseHelper(Application context) {
		super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		synchronized (getClass()) {
			createTables(db,DBConstants.TABLE_PERSON_INFO);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropTables(db);	
		onCreate(db);
	}
	
	public void resetAllTables(SQLiteDatabase db){
		dropTables(db);
		AppSharedPreferences.getInstance().clearAllData();
	}
	
	private void createTables(SQLiteDatabase db,String table){
		
	}
	
	private void dropTables(SQLiteDatabase db){
		
	}
}
