package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mTextQuestion ;
    private Button btnRight;
    private Button btnWrong;
    private int mQuestionIndex;

    private  QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, false),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, false),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, false),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextQuestion = findViewById(R.id.txtView1);

        QuizModel q1 = questionCollection[mQuestionIndex];

        mTextQuestion.setText(q1.getQuestion());

        btnRight = findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "btn True is tapped now!!!", Toast.LENGTH_LONG).show();

            }
        });

        btnWrong = findViewById(R.id.btnWrong);
        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "btn Wrong is tapped now!!!", Toast.LENGTH_LONG).show();

            }
        });
    }
}
