package com.example.button_ben.gpacalculator1subject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Display extends ActionBarActivity {

    public static final String GPADisplay = "com.example.button_ben.gpacalculator1subject.display";
    private TextView mscoretext1;
    private TextView mscoretext2;
    private int mCounter = 0;
    private double mGPA;
    private float mEarnedPercentage=0;
    private float mTotalPercentage=0;
    private ArrayList<Summative> mSummative = new ArrayList<Summative>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mscoretext1 = (TextView)findViewById(R.id.text1);
        mscoretext2 = (TextView)findViewById(R.id.text2);

        //getting the array list
        Intent intent = getIntent();
        mSummative = intent.getParcelableArrayListExtra(GPADisplay);

        //deriving the total weightage as well as the weightage earned
        while (mSummative.get(mCounter)!=null) {
            mTotalPercentage+=mSummative.get(mCounter).getTestWeight();
            mEarnedPercentage+= mSummative.get(mCounter).getMark()/mSummative.get(mCounter).getTotalMark()*mTotalPercentage;
            mCounter++;
        }

        //finding out the GPA
        if (mEarnedPercentage >= mTotalPercentage*0.8) {
            mGPA = 4.0;
        }

        else if (mEarnedPercentage >= mTotalPercentage*0.7) {
            mGPA = 3.6;
        }

        else if (mEarnedPercentage >= mTotalPercentage*0.65) {
            mGPA = 3.2;
        }

        else if (mEarnedPercentage >= mTotalPercentage*0.60) {
            mGPA = 2.8;
        }

        else if (mEarnedPercentage >= mTotalPercentage*0.55) {
            mGPA = 2.4;
        }

        else if (mEarnedPercentage >= mTotalPercentage*0.5) {
            mGPA = 2.0;
        }

        else if (mEarnedPercentage >= mTotalPercentage*0.45) {
            mGPA = 1.6;
        }

        else if (mEarnedPercentage >= mTotalPercentage*0.4) {
            mGPA = 1.2;
        }

        else {
            mGPA = 0.8;
        }

        //Displaying the results
        mscoretext1.setText("Your GPA for this subject is: " + mGPA + ".");
        mscoretext2.setText("You have achieved " + mEarnedPercentage +"% of " + mTotalPercentage + "% for this subject.");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
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
