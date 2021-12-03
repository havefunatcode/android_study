package com.example.intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Handler Object - Thread 간에 정보를 중고받을 수 있는 객체
    private Handler handler = new Handler() {
        public void handleMessage(Message message)  {
            Object path = message.obj;
            if(message.arg1 == RESULT_OK && path != null)   {
                Toast.makeText(getApplicationContext(), " " + path.toString() + "을 다운로드하였음", Toast.LENGTH_LONG).show();
            } else  {
                Toast.makeText(getApplicationContext(), "다운로드 실패", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v)   {
        Intent intent = new Intent(this, DownloadService.class);
        Messenger messenger = new Messenger(handler);
        intent.putExtra("MESSENGER", messenger);
        intent.putExtra("urlpath", "https://www.naver.com/");
        startService(intent);
    }
}
