package com.example.filetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String FILENAME = "test.txt";
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //외부저장소의 상태 읽기
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED) == false)    {
            Toast.makeText(this, "외부 스토리지 실패", Toast.LENGTH_LONG).show();
        }

        //내부저장소
        edit = (EditText) findViewById(R.id.EditText01);
        Button readButton = (Button) findViewById(R.id.read);
        readButton.setOnClickListener(new View.OnClickListener() {
            //File을 읽어들인다.
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(FILENAME);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    edit.setText(new String(buffer));   //byte배열을 String으로 변환
                    fis.close();
                } catch(IOException e) {}
            }
        });

        Button writeButton = (Button) findViewById(R.id.write);
        writeButton.setOnClickListener(new View.OnClickListener() {
            //File을 저장한다.
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(edit.getText().toString().getBytes());
                    fos.close();
                } catch(IOException e){}
            }
        });

        //외부저장소
        Button extReadButton = (Button) findViewById(R.id.extRead);
        extReadButton.setOnClickListener(new View.OnClickListener() {
            //파일 읽기
            @Override
            public void onClick(View v) {
                File file = new File(getExternalFilesDir(null), FILENAME); //디럭터리를 주지 않으면 root에다가 생성해라.
                try {
                    InputStream is;
                    is = new FileInputStream(file);
                    byte[] buffer = new byte[is.available()];
                    is.read(buffer);
                    edit.setText(new String(buffer));
                    is.close();
                } catch(Exception e)    {
                    e.printStackTrace();
                }
            }
        });

        Button extWriteButton = (Button) findViewById(R.id.extWrite);
        extWriteButton.setOnClickListener(new View.OnClickListener() {
            //파일 쓰기
            @Override
            public void onClick(View v) {
                File file = new File(getExternalFilesDir(null), FILENAME);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(edit.getText().toString().getBytes());
                    os.close();
                } catch(Exception e)    {
                    e.printStackTrace();
                }
            }
        });

    }
}
