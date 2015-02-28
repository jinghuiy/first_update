package com.example.button_ben.gpacalculator1subject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Button_ben on 2/24/2015.
 */
public class Summative implements Parcelable{
    //declaring components of the class
    private String mSummativeName;
    private float mTotalMark;
    private float mMark;
    private float mTestWeight;
    //private double mGPA;

    //Setting up the getters
    public String getName(){ return mSummativeName;}
    public float getTotalMark(){ return mTotalMark;}
    public float getMark(){ return mMark;}
    public float getTestWeight(){ return mTestWeight;}
    //public double getGPA(){ return mGPA;}

    //Setting up the setters
    public void setName(String summativeName){ mSummativeName = summativeName;}
    public void setTotalMark(float totalMark){ mTotalMark = totalMark;}
    public void setMark(float Mark){ mMark = Mark;}
    public void setTestWeight(float testWeight){ mTestWeight = testWeight;}
    //public void setGPA(double GPA) {mGPA = GPA;}


    private Summative(Parcel in){
        mSummativeName = in.readString();
        mTotalMark = in.readFloat();
        mMark = in.readFloat();
        mTestWeight = in.readFloat();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mSummativeName);
        out.writeFloat(mTotalMark);
        out.writeFloat(mMark);
        out.writeFloat(mTestWeight);
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Summative> CREATOR = new Parcelable.Creator<Summative>() {
        public Summative createFromParcel(Parcel in) {
            return new Summative(in);
        }

        public Summative[] newArray(int size) {
            return new Summative[size];
        }
    };
}
