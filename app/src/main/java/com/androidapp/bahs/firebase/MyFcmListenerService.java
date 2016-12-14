package com.androidapp.bahs.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFcmListenerService extends FirebaseMessagingService {
  @Override
  public void onMessageReceived(RemoteMessage message){
   /* String from = message.getFrom();
    Map data = message.getData();*/
  }

}