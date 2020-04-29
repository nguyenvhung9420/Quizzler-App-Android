package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    android.widget.Button buttonTrue;
    android.widget.Button buttonFalse;
    android.widget.TextView mQuestionTextView;
    android.widget.ProgressBar mProgressBar;
    android.widget.TextView mScoreTextView;
    int mIndex = 0;
    int mScore = 0;


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };

    int PROGRESS_INCREMENT = (int) Math.ceil(100 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTrue = findViewById(R.id.true_button);
        buttonFalse = findViewById(R.id.false_button);
        mScoreTextView = findViewById(R.id.score);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setText(mQuestionBank[mIndex].getQuestionID());

        mProgressBar = findViewById(R.id.progress_bar);

        if (savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            mScoreTextView.setText(String.format("Score: %s/%s", mScore, mQuestionBank.length));
        } else {
            mScore = 0;
            mIndex = 0;
        }

        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mQuestionBank[mIndex].isAnswer() == true) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    addScore();
                    updateQuestion();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Answer :(", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mQuestionBank[mIndex].isAnswer() == false) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    addScore();
                    updateQuestion();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Answer :(", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


    }

    private void addScore() {
        mScore++;
        mScoreTextView.setText(String.format("Score: %s/%s", mScore, mQuestionBank.length));
    }

    private void updateQuestion() {
        mIndex = (mIndex + 1) % mQuestionBank.length;
        mProgressBar.incrementProgressBy(PROGRESS_INCREMENT);
        mQuestionTextView.setText(mQuestionBank[mIndex].getQuestionID());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
    }
}
