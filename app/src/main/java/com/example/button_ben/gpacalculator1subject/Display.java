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

    public static final String GPADisplay = "com.example.button_ben.gpacalculator1subject.gpadisplay";
    public static final String EarnedDisplay = "com.example.button_ben.gpacalculator1subject.earndisplay";
    public static final String TotalDisplay = "com.example.button_ben.gpacalculator1subject.totaldisplay";
    private TextView mscoretext1;
    private TextView mscoretext2;
    private double mGPA;
    private float mEarnedPercentage=0;
    private float mTotalPercentage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mscoretext1 = (TextView)findViewById(R.id.text1);
        mscoretext2 = (TextView)findViewById(R.id.text2);

        //getting the imported values
        Intent intent = getIntent();
        mGPA = Double.parseDouble(intent.getStringExtra(GPADisplay));
        mTotalPercentage = Float.parseFloat(intent.getStringExtra(TotalDisplay));
        mEarnedPercentage = Float.parseFloat(intent.getStringExtra(EarnedDisplay));

        //deriving the total weightage as well as the weightage earned


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
