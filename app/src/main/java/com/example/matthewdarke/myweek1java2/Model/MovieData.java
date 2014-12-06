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
    private String mYear;


    private String mRate;
    private String mRunT;
    private String mThumb;
    //private ArrayList<String> castList;

    public MovieData() {
    }

    public MovieData(String title, String year, String mpaa_rating, String runtime, String thumbnail) {
        mTitle = title;
        mYear = year;
        mRate = mpaa_rating;
        mRunT = runtime;
        mThumb = thumbnail;
       // castList = cast;

    }

    public MovieData(JSONObject MovieData) {
        try {
            mTitle = MovieData.getString("title");
            mYear = MovieData.getString("year");
            mRate = MovieData.getString("mpaa_rating");
            mRunT = MovieData.getString("runtime");
            mThumb = MovieData.getString("thumbnail");
            //castList = MovieData.getString("cast");

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

    public String getmYear() {
        return mYear;
    }

    public void setmYear(String mYear) {
        this.mYear = mYear;
    }

    public String getmRate() {
        return mRate;
    }

    public void setmRate(String mRate) {
        this.mRate = mRate;
    }

    public String getmRunT() {
        return String.valueOf(mRunT);
    }

    public void setmRunT(String mRunT) {
        this.mRunT = mRunT;
    }

    public String getmThumb() {
        return mThumb;
    }

    public void setmThumb(String mThumb) {
        this.mThumb = mThumb;
    }

    //public String getCastList() {

      //  return TextUtils.join(", ", castList);

public MovieData(Bundle b) {

    if (b !=null) {
        this.mTitle = b.getString("title");
        this.mYear = b.getString("year");
        this.mRunT = b.getString("runtime");
        this.mRate = b.getString("mpaa_rating");
        this.mThumb = b.getString("thumbnail");

    }
}


    public Bundle toBundle() {

        Bundle bundle = new Bundle();
        MovieData movieData = new MovieData();
        bundle.putString("title", movieData.getmTitle());
        bundle.putString("year", movieData.getmYear());
        bundle.putString("mpaa_rating", movieData.getmRate());
        bundle.putString("runtime", movieData.getmRunT());
        bundle.putString("thumbnail", movieData.getmThumb());
        return bundle;
    }

}
