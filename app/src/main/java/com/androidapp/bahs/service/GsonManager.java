package com.androidapp.bahs.service;

import com.google.gson.Gson;

/**
 * Created by Mobikasa on 1/4/2017.
 */
public class GsonManager {
    private static GsonManager ourInstance = new GsonManager();

    public static GsonManager getInstance() {
        return ourInstance;
    }

    private GsonManager() {
    }

    private String toJSON(Object obj){
        Gson gson=new Gson();
        String json=gson.toJson(obj);
        return json;
    }

    private Object fromJSON(String data,Class className ){
        Gson gson=new Gson();
        Object value= gson.fromJson(data,className);
        return value;
    }
}
