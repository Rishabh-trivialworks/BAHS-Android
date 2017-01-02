package com.androidapp.bahs.service.db;


public class DBConstants {

	public static final String DATABASE_NAME = "BAHS.db";
	public static final int DATABASE_VERSION = 1;
	

	public static String TABLE_PERSON_INFO="create table person_basic_info"+
			"(id integer primary key,f_name text,l_name text,eamil text,profile_pic text)";

}
