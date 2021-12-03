package com.example.intentservice;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class DownloadService extends IntentService {
    // 취소된 상태로 시작
    private int result = Activity.RESULT_CANCELED;

    public DownloadService()    {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String urlPath = intent.getStringExtra("urlpath");
        String buffer = " ";

        InputStream stream = null;

        try {
            URL url = new URL(urlPath);
            // url과 service를 연결 시켜주기위한 코드
            stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            int i = 0, next =  - 1;
            while((next = reader.read()) != -1) {
                buffer += "" + (char) next;
                if(++i > 100) break;
            }
            // 정상적으로 작동된 경우
            result = Activity.RESULT_OK;
        } catch(Exception e)    {
            e.printStackTrace();
        } finally   {
            if(stream != null)  {
                try {
                    stream.close();
                } catch(IOException e)    {
                    e.printStackTrace();
                }
            }
        }

        Bundle extras = intent.getExtras();
        if(extras != null)  {
            Messenger messenger = (Messenger) extras.get("MESSENGER");
            Message message = Message.obtain();
            message.arg1 = result;
            message.obj = buffer;
            try {
                messenger.send(message);
            } catch(android.os.RemoteException e1) {
                Log.w(getClass().getName(), "Exception sending message", e1);
            }
        }
    }
}