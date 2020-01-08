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

    // Declaring instance variables(fields)
    private TextView mTextQuestion ;
    private Button btnRight;
    private Button btnWrong;
    private int mQuestionIndex;
    private int mQuizQuestion;

    // An array Instance variable of type QuizModel, which contains the needed quiestions
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

        //Initializing the mTextQustion Variable
        mTextQuestion = findViewById(R.id.txtView1);

        QuizModel q1 = questionCollection[mQuestionIndex];

        mTextQuestion.setText(q1.getQuestion());

        btnRight = findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEvaluateUserAnswer(true);
                mUpdateQuestionOnButtonClick();

            }
        });

        btnWrong = findViewById(R.id.btnWrong);
        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEvaluateUserAnswer(false);
                mUpdateQuestionOnButtonClick();

            }
        });
    }

    private  void mUpdateQuestionOnButtonClick(){

        mQuestionIndex = (mQuestionIndex + 1) % 10;
        mQuizQuestion = questionCollection[mQuestionIndex].getQuestion();
        mTextQuestion.setText(mQuizQuestion);

    }

    private void mEvaluateUserAnswer(boolean userGuess){

        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].isAnswer();
        if (currentQuestionAnswer == userGuess){
            Toast.makeText(getApplicationContext(), R.string.correct_text, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_text, Toast.LENGTH_LONG).show();
        }
    }

}
