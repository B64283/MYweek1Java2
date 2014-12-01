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
    private Integer mYear;


    private String mRate;
    private Double mRunT;
    private String mThumb;


    public MovieData() {
    }

    public MovieData(String title, Integer year, String mpaa_rating, Double runtime, String thumbnail) {
        mTitle = title;
        mYear = year;
        mRate = mpaa_rating;
        mRunT = runtime;
        mThumb = thumbnail;

    }

    public MovieData(JSONObject MovieData) {
        try {
            mTitle = MovieData.getString("title");
            mYear = MovieData.getInt("year");
            mRate = MovieData.getString("mpaa_rating");
            mRunT = MovieData.getDouble("runtime");
            mThumb = MovieData.getString("thumbnail");

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

    public Integer getmYear() {
        return mYear;
    }

    public void setmYear(Integer mYear) {
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
