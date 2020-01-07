package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRight = findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "btn True is tapped now!!!");
            }
        });

        Button btnWrong = findViewById(R.id.btnWrong);
        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp", "btn Wrong is tapped now!!!");
            }
        });
//
//        View.OnClickListener myClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.btnRight){
//                    Log.i("MyApp", "btn True is tapped now!!!");
//                } else if (v.getId() == R.id.btnWrong){
//                    Log.i("MyApp", "btn Wrong is tapped now!!!");
//                }
//            }
//        };
//
//        btnRight.setOnClickListener(myClickListener);
//        Button btnWrong = findViewById(R.id.btnWrong);
//        btnWrong.setOnClickListener(myClickListener);
    }
}
