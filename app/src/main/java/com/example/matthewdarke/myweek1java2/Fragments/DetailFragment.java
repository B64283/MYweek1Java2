package com.example.matthewdarke.myweek1java2.Fragments;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.matthewdarke.myweek1java2.R;

/**
 * Created by matthewdarke on 11/25/14.
 */
public class DetailFragment extends Fragment {


    //no  arguments constructor
    public DetailFragment() {


    }


    //on create is automaticly called by framework inflates layout and returns resulting view
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.detail_fragment, container, false);

       //returns root view when requested... Now when the instance of this fragment class is added to
       //an activity, the oncall method will be called then it will create the view and return it and display it to the screen
        return rootView;
    }
}
