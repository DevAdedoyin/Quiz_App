package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private final String SCORE_KEY = "SCORE";
    private final String INDEX_KEY = "INDEX";

    // Declaring instance variables(fields)
    private TextView mTextQuestion ;
    private TextView mTextStats;
    private Button btnRight;
    private Button btnWrong;
    private ProgressBar mProgressBar;

    private int mQuestionIndex;
    private int mQuizQuestion;

    private int mUserScore;

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

    final int USER_PROG = (int) Math.ceil(100 / questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( savedInstanceState != null){
            mUserScore = savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex = savedInstanceState.getInt(INDEX_KEY);
        } else {
            mUserScore = 0;
            mQuestionIndex = 0;
        }

        mTextStats = findViewById(R.id.txtView2);
        mTextStats.setText(mUserScore + "");
        mProgressBar = findViewById(R.id.prog_bar);

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
        if (mQuestionIndex == 0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The quiz is finished");
            quizAlert.setMessage("Your score is: " + mUserScore);
            quizAlert.setPositiveButton("Exit Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quizAlert.show();

        }
        mQuizQuestion = questionCollection[mQuestionIndex].getQuestion();
        mTextQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROG);
        mTextStats.setText(mUserScore + "");
    }

    private void mEvaluateUserAnswer(boolean userGuess){

        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].isAnswer();
        if (currentQuestionAnswer == userGuess){
            Toast.makeText(getApplicationContext(), R.string.correct_text, Toast.LENGTH_SHORT).show();
            mUserScore+=1;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_text, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionIndex);

    }
}
