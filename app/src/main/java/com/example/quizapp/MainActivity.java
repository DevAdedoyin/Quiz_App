package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRight = findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "btn True is tapped now!!!", Toast.LENGTH_LONG).show();

            }
        });

        Button btnWrong = findViewById(R.id.btnWrong);

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "btn Wrong is tapped now!!!", Toast.LENGTH_LONG).show();

            }
        });

        QuizModel myQuizModel = new QuizModel(R.string.q1, true);

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
