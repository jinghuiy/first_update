package com.example.button_ben.gpacalculator1subject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Button mGoButton;
    private Button mNextButton;
    private Toast mToastedBread;
    private EditText mMarksText;
    private EditText mTotalMarksText;
    private EditText mWeightageText;
    private EditText mAssessmentText;
    private float mMarks;
    private float mTotalMarks;
    private String mAssessment;
    private float mWeightage;
    private double mGPA;
    private ArrayList<Summative> mSummative = new ArrayList<Summative>();
    private Summative mAssignment = new Summative();
    private float mTotalPercentage = 0;
    private float mEarnedPercentage = 0;
    private int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoButton = (Button)findViewById(R.id.go_button);
        mNextButton = (Button)findViewById(R.id.next_button);
        mMarksText = (EditText)findViewById(R.id.marks_text);
        mTotalMarksText = (EditText)findViewById(R.id.total_marks_text);
        mWeightageText = (EditText)findViewById(R.id.weightage_text);
        mAssessmentText = (EditText)findViewById(R.id.assessment_text);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks if all fields have been filled
                if (mMarksText.getText().toString() ==  "" || mTotalMarksText.getText().toString() == "" || mAssessmentText.getText().toString() == "" || mWeightageText.getText().toString() == "") {
                    if (mToastedBread!=null){
                        mToastedBread.cancel();
                    }
                    mToastedBread = Toast.makeText(MainActivity.this, "Not all fields have been filled. Please check.", Toast.LENGTH_LONG);
                    mToastedBread.show();
                }
                //Checks for errors in the inputted total marks
                else if (Float.parseFloat(mTotalMarksText.getText().toString()) <= 0){
                    if (mToastedBread!=null){
                        mToastedBread.cancel();
                    }
                    mToastedBread = Toast.makeText(MainActivity.this, "The total marks cannot be 0 or less than 0. Please check.", Toast.LENGTH_LONG);
                    mToastedBread.show();
                }

                else {
                    //Converts inputted values into readable values that can be used
                    mMarks = Float.parseFloat(mMarksText.getText().toString());
                    mTotalMarks = Float.parseFloat(mTotalMarksText.getText().toString());
                    mAssessment = mAssessmentText.getText().toString();
                    mWeightage = Float.parseFloat(mWeightageText.getText().toString());

                    //mAssignment.setGPA(mGPA);
                    //adds values into the array list of summatives
                    mAssignment.setMark(mMarks);
                    mAssignment.setTotalMark(mTotalMarks);
                    mAssignment.setName(mAssessment);
                    mAssignment.setTestWeight(mWeightage);
                    mSummative.add(mAssignment);

                    //notifies tht user that the next set of values can be inputted
                    if (mToastedBread!=null){
                        mToastedBread.cancel();
                    }
                    mToastedBread = Toast.makeText(MainActivity.this, "Details inputted. Please input next results or click 'Calculate'.", Toast.LENGTH_LONG);
                    mToastedBread.show();

                    //resets the EditTexts for next input
                    mAssessmentText.setText("");
                    mMarksText.setText("");
                    mTotalMarksText.setText("");
                    mWeightageText.setText("");
                }
            }
        });

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks if user has inputted anything
                if (mSummative.size() == 0) {
                    if (mToastedBread!=null) {
                        mToastedBread.cancel();
                    }
                    mToastedBread = Toast.makeText(MainActivity.this, "You have not inputted anything.", Toast.LENGTH_LONG);
                    mToastedBread.show();
                }

                else {
                    while (mCounter < mSummative.size()) {
                        mTotalPercentage += mSummative.get(mCounter).getTestWeight();
                        mEarnedPercentage += (mSummative.get(mCounter).getMark() / mSummative.get(mCounter).getTotalMark()) * mSummative.get(mCounter).getTestWeight();
                        mCounter++;
                    }

                    //finding out the GPA
                    if (mEarnedPercentage >= mTotalPercentage * 0.8) {
                        mGPA = 4.0;
                    }

                    else if (mEarnedPercentage >= mTotalPercentage * 0.7) {
                        mGPA = 3.6;
                    }

                    else if (mEarnedPercentage >= mTotalPercentage * 0.65) {
                        mGPA = 3.2;
                    }

                    else if (mEarnedPercentage >= mTotalPercentage * 0.60) {
                        mGPA = 2.8;
                    }

                    else if (mEarnedPercentage >= mTotalPercentage * 0.55) {
                        mGPA = 2.4;
                    }

                    else if (mEarnedPercentage >= mTotalPercentage * 0.5) {
                        mGPA = 2.0;
                    }

                    else if (mEarnedPercentage >= mTotalPercentage * 0.45) {
                        mGPA = 1.6;
                    }

                    else if (mEarnedPercentage >= mTotalPercentage * 0.4) {
                        mGPA = 1.2;
                    }

                    else {
                        mGPA = 0.8;
                    }

                    //sets the values to be imported
                    Intent intent = new Intent(MainActivity.this, Display.class);

                    intent.putExtra(Display.GPADisplay, mGPA + "");
                    intent.putExtra(Display.EarnedDisplay, mEarnedPercentage + "");
                    intent.putExtra(Display.TotalDisplay, mTotalPercentage + "");
                    //imports values
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
