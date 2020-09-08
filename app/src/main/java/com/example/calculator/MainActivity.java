package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtNum1, edtNum2, edtResult;
    private Button btnAdd, btnSub, btnMul, btnDiv;

    @Override
    public void onClick(View v) {
        String number1 = edtNum1.getText().toString();
        String number2 = edtNum2.getText().toString();
        double result = Double.parseDouble(number1) / Double.parseDouble(number2);
        edtResult.setText(" "+result);
    }

    class Listener implements View.OnClickListener   {

        @Override
        public void onClick(View v) {
            String number1 = edtNum1.getText().toString();
            String number2 = edtNum2.getText().toString();
            double result = Double.parseDouble(number1) + Double.parseDouble(number2);
            edtResult.setText(" "+result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNum1 = findViewById(R.id.edtNum1);
        edtNum2 = findViewById(R.id.edtNum2);
        edtResult = findViewById(R.id.edtResult);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        Listener lis = new Listener();
        btnAdd.setOnClickListener(lis);
        btnSub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String number1 = edtNum1.getText().toString();
                String number2 = edtNum2.getText().toString();
                double result = Double.parseDouble(number1) - Double.parseDouble(number2);
                edtResult.setText(" "+result);
            }
        });
        btnDiv.setOnClickListener(this);
        btnMul.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String number1 = edtNum1.getText().toString();
                String number2 = edtNum2.getText().toString();
                double result = Double.parseDouble(number1) * Double.parseDouble(number2);
                edtResult.setText(" "+result);
            }
        });
    }
}
