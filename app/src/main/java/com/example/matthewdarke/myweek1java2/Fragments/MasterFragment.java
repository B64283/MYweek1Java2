package com.example.matthewdarke.myweek1java2.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import com.example.matthewdarke.myweek1java2.Model.MovieData;
import com.example.matthewdarke.myweek1java2.MovieArrayAdapter;
import com.example.matthewdarke.myweek1java2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthewdarke on 11/25/14.
 */
public class MasterFragment extends ListFragment {

    public static final String TAG = "MasterFragment.TAG";

   //the Activity context is stored in a member reference mListener.
   // This way, the Fragment can refer back to
   // the containing Activity, allowing the Fragment to communicate with the Activity as needed

     private OnListCallBack mListener;


    //private String mSearchWord;
    // public Button mSearchButton;
    public MasterFragment masterFragment;
    //ArrayAdapter<MovieData> movieDataList;
    public ArrayAdapter<MovieData> movieDataList;
    ListView mMoviesList;



    public MasterFragment() {

    }

@Override
public void onCreate(Bundle SavedInstanceState) {
super.onCreate(SavedInstanceState);

}


    public static MasterFragment newInstance(ArrayList<MovieData> mMoviesList) {

        return new MasterFragment();


}///////////INTERFACE CALLBACK

    public interface OnListCallBack {
        public void onItemSelected(String detailStr);

    }





    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof OnListCallBack) {
             mListener = (OnListCallBack) activity;
        } else {
            throw new IllegalArgumentException("Containing activity must implement OnSearchListener interface");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle SavedInstanceState) {

        return inflater.inflate(R.layout.master_fragment, container, false);



        //mSearchButton.setOnClickListener(new View.OnClickListener() {

        //returns root view when requested... Now when the instance of this fragment class is added to
        //an activity, the oncall method will be called then it will create the view and return it and display it to the screen


    }

    @Override
    public void onActivityCreated(Bundle _SavedInstanceState) {
        super.onActivityCreated(_SavedInstanceState);


        ArrayAdapter<MovieData> arrayAdapter = new ArrayAdapter<MovieData>(masterFragment.getActivity(), android.R.layout.simple_list_item_1,movieDataList);

        movieDataList.findViewById(R.id.master_container);
        setListAdapter(arrayAdapter);
    }





//on create is automatically called by framework inflates layout and returns resulting view







    @Override


    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        System.out.println("the button is working");
        //mListener.displayDetails();
        MovieData movieData = movieDataList.get(position);


        Bundle bundle = new Bundle();
        DetailFragment fragment = new DetailFragment();

        bundle.putString("title", movieData.getmTitle());
        bundle.putString("year", movieData.getmYear());
        bundle.putString("mpaa_rating", movieData.getmRate());
        bundle.putString("runtime", movieData.getmRunT());
        bundle.putString("thumbnail", movieData.getmThumb());


        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.detail_viewer, fragment).commit();


    }




}





