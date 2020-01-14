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

//     (1) Declaring instance variables(fields)
    private final String SCORE_KEY = "SCORE";
    private final String INDEX_KEY = "INDEX";

    private TextView mTextQuestion ;
    private TextView mTextStats;

    private Button btnRight;
    private Button btnWrong;


    private int mQuestionIndex;
    private int mQuizQuestion;

    private ProgressBar mProgressBar;
    private int mUserScore;

//     (1) An array Instance variable of type QuizModel, which contains the needed quiestions
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

//         (1) These conditional statements, checks to see if the savedInstance is null...
//         (2) If null, it means the mode(Landscape or Portrait) of the app haven't changed...
//              These means the current score and index is still equal to Zero(0)...
//         (3) If not null, it means the mode(Landscape or Portrait) of the app changed...
//              These ensures the User's Score and Index remains the same as when it was in the previous mode(Portrait or Landscape)...
        if ( savedInstanceState != null){
            mUserScore = savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex = savedInstanceState.getInt(INDEX_KEY);
        } else {
            mUserScore = 0;
            mQuestionIndex = 0;
        }

//         (1) The Second TextView object used for showing user score is being initialized, by getting its id...
//         (2)The text of the text view is also set, to show user score...
        mTextStats = findViewById(R.id.txtView2);
        mTextStats.setText(mUserScore + "");

//         (1) The ProgressBar object is being initialized by getting its id...
        mProgressBar = findViewById(R.id.prog_bar);

//         (1) An instance of the QuizModel class is created...
        QuizModel q1 = questionCollection[mQuestionIndex];

//        (1) The First TextView object, showing the questions is being initialized by getting its id...
        mTextQuestion = findViewById(R.id.txtView1);
        mTextQuestion.setText(q1.getQuestion());

//        (1) The True button object is being initialized by getting its id...
//        (2) The True button onClickListener is set...
        btnRight = findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEvaluateUserAnswer(true);
                mUpdateQuestionOnButtonClick();

            }
        });

//       (1) The Wrong button object is being initialized by getting its id...
//       (2) The Wrong button onClickListener is set...
        btnWrong = findViewById(R.id.btnWrong);
        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEvaluateUserAnswer(false);
                mUpdateQuestionOnButtonClick();

            }
        });
    }

//     (1) This mUpdateQuestionOnButtonClick method is called whenever the True and Wrong button is being clicked...
//     (2) The if condition checks whether the whole question as been asked and is to start again...
//          -If the question as being asked the AlertDialog object is shown to see the final score...
//     (3) The next question will be shown and the ProgressBar is increased by USER_PROG variable...
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

//     (1) This mEvaluateUserAnswer method is called to evaluate the user's answer...
//     (2) If the user's answer is correct the user's score increases by 1...
//     (3) Else if the user's answer is wrong the user's score remains the same...
    private void mEvaluateUserAnswer(boolean userGuess){
        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].isAnswer();
        if (currentQuestionAnswer == userGuess) {
            Toast.makeText(getApplicationContext(), R.string.correct_text, Toast.LENGTH_SHORT).show();
            mUserScore+=1;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_text, Toast.LENGTH_SHORT).show();
        }
    }

//     (1) The onSaveInstanceState method is being called to get the current state of the app and save the given values...
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionIndex);

    }
}
