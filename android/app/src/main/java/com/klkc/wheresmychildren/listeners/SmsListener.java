package com.klkc.wheresmychildren.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;

/**
 * Created by przetocki on 14.03.16.
 */
public class SmsListener extends BroadcastReceiver {
    private SharedPreferences preferences;
    public static String msgBody;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
        SmsMessage[] msgs = null;
        String msg_from;
        if (bundle != null){
            //---retrieve the SMS message received---
            try{
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                for(int i=0; i<msgs.length; i++){
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    msg_from = msgs[i].getOriginatingAddress();
                    msgBody = msgs[i].getMessageBody();
                }
            }catch(Exception e){
//                            Log.d("Exception caught",e.getMessage());
            }
        }
    }

    public static void update(TextView tv){
        tv.setText(msgBody);
    }
}
