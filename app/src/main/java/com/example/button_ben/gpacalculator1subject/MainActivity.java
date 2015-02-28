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
    //private double mGPA;
    private ArrayList<Summative> mSummative = new ArrayList<Summative>();
    private Summative mAssignment;

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
                    mToastedBread = Toast.makeText(MainActivity.this, "Not all fields have been filled. Please check.", Toast.LENGTH_LONG);
                    mToastedBread.show();
                }
                //Checks for errors in the inputted total marks
                else if (Float.parseFloat(mTotalMarksText.getText().toString()) <= 0){
                    mToastedBread = Toast.makeText(MainActivity.this, "The total marks cannot be less than 0. Please check.", Toast.LENGTH_LONG);
                    mToastedBread.show();
                }

                else {
                    //Converts inputted values into readable values that can be used
                    mMarks = Float.parseFloat(mMarksText.getText().toString());
                    mTotalMarks = Float.parseFloat(mTotalMarksText.getText().toString());
                    mAssessment = mAssessmentText.getText().toString();
                    mWeightage = Float.parseFloat(mWeightageText.getText().toString());

                    //mAssignment.setGPA(mGPA);
                    //adds values into the array list to be imported over to the next activity
                    mAssignment.setMark(mMarks);
                    mAssignment.setTotalMark(mTotalMarks);
                    mAssignment.setName(mAssessment);
                    mAssignment.setTestWeight(mWeightage);
                    mSummative.add(mAssignment);
                    //notifies tht user that the next set of values can be inputted
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
                //sets the array list to be imported
                Intent intent = new Intent(MainActivity.this, Display.class);
                intent.putParcelableArrayListExtra(Display.GPADisplay, mSummative);
                //imports array list
                startActivity(intent);
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
