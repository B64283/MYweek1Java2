package com.example.matthewdarke.myweek1java2.Model;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by matthewdarke on 11/22/14.
 */
public class MovieData {


    final String TAG = "MOVIEDATA CLASS";

    private String mTitle;
    private Double mYear;


    private String mRate;
    private Double mRunT;
    private String mThumb;


    public MovieData() {
    }

    public MovieData(String title, Double year, String mpaa_rating, Double runtime, String thumbnail) {
        mTitle = title;
        mYear = year;
        mRate = mpaa_rating;
        mRunT = runtime;
        mThumb = thumbnail;

    }

    public MovieData(JSONObject stockData) {
        try {
            mTitle = stockData.getString("title");
            mYear = stockData.getDouble("year");
            mRate = stockData.getString("mpaa_rating");
            mRunT = stockData.getDouble("runtime");
            mThumb = stockData.getString("thumbnail");

        } catch (Exception e) {
            Log.e(TAG, "Error updating display");
        }
    }


    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Double getmYear() {
        return mYear;
    }

    public void setmYear(Double mYear) {
        this.mYear = mYear;
    }

    public String getmRate() {
        return mRate;
    }

    public void setmRate(String mRate) {
        this.mRate = mRate;
    }

    public Double getmRunT() {
        return mRunT;
    }

    public void setmRunT(Double mRunT) {
        this.mRunT = mRunT;
    }

    public String getmThumb() {
        return mThumb;
    }

    public void setmThumb(String mThumb) {
        this.mThumb = mThumb;
    }

    public Bundle toBundle() {

        Bundle bundle = new Bundle();
        MovieData movieData = new MovieData();
        bundle.putString("title", movieData.getmTitle());
        bundle.putDouble("year", movieData.getmYear());
        bundle.putString("mpaa_rating", movieData.getmRate());
        bundle.putDouble("runtime", movieData.getmRunT());
        bundle.putString("thumbnail", movieData.getmThumb());
        return bundle;
    }

}
