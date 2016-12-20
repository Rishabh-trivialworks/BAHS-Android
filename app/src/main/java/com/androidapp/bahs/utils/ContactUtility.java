package com.androidapp.bahs.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import com.androidapp.bahs.service.bean.User;
import com.androidapp.bahs.service.utils.LogUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ContactUtility {

	public static List<User> getEmail(Context context) {

		return null;
	}

	public static ArrayList<String> getNameEmailDetails(Context context) {
	    ArrayList<String> emlRecs = new ArrayList<String>();
	    HashSet<String> emlRecsHS = new HashSet<String>();
	     
	    ContentResolver cr = context.getContentResolver();
	    String[] PROJECTION = new String[] { ContactsContract.RawContacts._ID,
	            ContactsContract.Contacts.DISPLAY_NAME,
	            ContactsContract.Contacts.PHOTO_ID,
	            ContactsContract.CommonDataKinds.Email.DATA,
	            ContactsContract.CommonDataKinds.Photo.CONTACT_ID };
	    String order = "CASE WHEN "
	            + ContactsContract.Contacts.DISPLAY_NAME
	            + " NOT LIKE '%@%' THEN 1 ELSE 2 END, " 
	            + ContactsContract.Contacts.DISPLAY_NAME
	            + ", " 
	            + ContactsContract.CommonDataKinds.Email.DATA
	            + " COLLATE NOCASE";
	    String filter = ContactsContract.CommonDataKinds.Email.DATA + " NOT LIKE ''";
	    Cursor cur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, PROJECTION, filter, null, order);
	    
	    LogUtils.info("@@@@@@@@@@@@@@  cursor size =" + cur.getCount());
	    if (cur.moveToFirst()) {
	        do {
	        	 String nm=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	        	 String img=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
	        	 String email=cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
	             
	        	 String id = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Photo.CONTACT_ID));
	        	 long contactId = Long.parseLong(id);
	        	 
	        	 LogUtils.info("@@@@@@@@@@@@@11      =" + nm);
	        	 LogUtils.info("@@@@@@@@@@@@@11      =" + getContactPhotoUri(contactId));
	        	 LogUtils.info("@@@@@@@@@@@@@11      =" + email);
	        	 
	            // names comes in hand sometimes
	            String name = cur.getString(1);
	            String emlAddr = cur.getString(3);
	            
	            LogUtils.info("@@@@@@@@@@@@@@22      =" + name);
	        	 LogUtils.info("@@@@@@@@@@@@@22      =" + emlAddr);
	        	 

	            // keep unique only
	            if (emlRecsHS.add(emlAddr.toLowerCase())) {
	                emlRecs.add(emlAddr);
	            }
	        } while (cur.moveToNext());
	    }

	    cur.close();
	    return emlRecs;
	}
	
	
	public static Uri getContactPhotoUri(long contactId) {
	    Uri photoUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
	    photoUri = Uri.withAppendedPath(photoUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
	    return photoUri;
	}

	public static ArrayList<User> getNameEmailPhoto(Context context) {
		ArrayList<User> emlRecs = new ArrayList<User>();
		HashSet<String> emlRecsHS = new HashSet<String>();

		ContentResolver cr = context.getContentResolver();
		String[] PROJECTION = new String[] { ContactsContract.RawContacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.PHOTO_ID,
				ContactsContract.CommonDataKinds.Email.DATA,
				ContactsContract.CommonDataKinds.Photo.CONTACT_ID };
		String order = "CASE WHEN "
				+ ContactsContract.Contacts.DISPLAY_NAME
				+ " NOT LIKE '%@%' THEN 1 ELSE 2 END, "
				+ ContactsContract.Contacts.DISPLAY_NAME
				+ ", "
				+ ContactsContract.CommonDataKinds.Email.DATA
				+ " COLLATE NOCASE";
		String filter = ContactsContract.CommonDataKinds.Email.DATA + " NOT LIKE ''";
		Cursor cur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, PROJECTION, filter, null, order);


		if (cur.moveToFirst()) {
			do {
				String nm=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				String img=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
				String email=cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

				String id = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Photo.CONTACT_ID));
				long contactId = Long.parseLong(id);

				// names comes in hand sometimes
				String name = cur.getString(1);
				Bitmap Photo=getContactBitmap(contactId,context);
				String emlAddr = cur.getString(3);


				// keep unique only
				if (emlRecsHS.add(emlAddr.toLowerCase())) {
					User muser=new User();
					muser.setId(String.valueOf(contactId));
					muser.setName(name);
					muser.setimage(Photo);
					muser.setEmail(emlAddr);
					muser.setSeleted(false);
					emlRecs.add(muser);
				}
			} while (cur.moveToNext());
		}

		cur.close();
		return emlRecs;
	}
	public static Bitmap getContactBitmap(long contactId, Context context) {
		Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
		Uri displayPhotoUri = Uri.withAppendedPath(contactUri,
				ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
		try {
			AssetFileDescriptor fd = context.getContentResolver()
					.openAssetFileDescriptor(displayPhotoUri, "r");
			BufferedInputStream buf = new BufferedInputStream(
					fd.createInputStream());
			Bitmap my_btmp = BitmapFactory.decodeStream(buf);
			return my_btmp;
		} catch (IOException e) {
			return null;
		}
	}
}
