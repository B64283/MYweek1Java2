package com.example.matthewdarke.myweek1java2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.matthewdarke.myweek1java2.Model.MovieData;

import java.util.List;

/**
 * Created by matthewdarke on 11/26/14.
 */
public class MovieArrayAdapter extends ArrayAdapter<MovieData> {

    private Context context;
    private List<MovieData> mMoviesList;

    public MovieArrayAdapter(Context context, int resource, List<MovieData> objects) {

        super(context, resource, objects);
        this.context = context;
        this.mMoviesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.movie_listitem, null);

        MovieData movieData = mMoviesList.get(position);
        TextView tv = (TextView)view.findViewById(R.id.textView_list);

        tv.setText(movieData.getmTitle());

        return view;

    }

}
