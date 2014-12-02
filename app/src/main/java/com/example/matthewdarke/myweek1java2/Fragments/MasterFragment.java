package com.example.matthewdarke.myweek1java2.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.matthewdarke.myweek1java2.Model.MovieData;
import com.example.matthewdarke.myweek1java2.MovieArrayAdapter;
import com.example.matthewdarke.myweek1java2.R;

import java.util.List;

/**
 * Created by matthewdarke on 11/25/14.
 */
public class MasterFragment extends ListFragment {

    public static final String TAG = "MasterFragment.TAG";

   //the Activity context is stored in a member reference mListener.
   // This way, the Fragment can refer back to
   // the containing Activity, allowing the Fragment to communicate with the Activity as needed


    private OnSearchListener mListener;
    //private String mSearchWord;
    // public Button mSearchButton;
    public MasterFragment masterFragment;
    //ArrayAdapter<MovieData>
    List<MovieData> movieDataList;
    public ListView moviesList;

    public static MasterFragment newInstance() {
        return new MasterFragment();


}///////////INTERFACE CALLBACK

    public interface OnSearchListener {
        public void searchForSomething(String text);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof OnSearchListener) {
            mListener = (OnSearchListener) activity;
        } else {
            throw new IllegalArgumentException("Containing activity must implement OnSearchListener interface");
        }
    }

    public MasterFragment() {

}



    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);


        MovieArrayAdapter adapter = new MovieArrayAdapter(masterFragment.getActivity(), android.R.layout.simple_list_item_1,movieDataList);

        moviesList.findViewById(R.id.master_container);
        setListAdapter(adapter);
    }





//on create is automatically called by framework inflates layout and returns resulting view
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.master_fragment, container, false);



        //mSearchButton.setOnClickListener(new View.OnClickListener() {

        //returns root view when requested... Now when the instance of this fragment class is added to
        //an activity, the oncall method will be called then it will create the view and return it and display it to the screen

        return rootView;
    }






    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        System.out.println("the button is working");
        //mListener.displayDetails();
        MovieData movieData = movieDataList.get(position);


        Bundle bundle = new Bundle();
        DetailFragment fragment = new DetailFragment();

        bundle.putString("title", movieData.getmTitle());
        bundle.putDouble("year", movieData.getmYear());
        bundle.putString("mpaa_rating", movieData.getmRate());
        bundle.putDouble("runtime", movieData.getmRunT());
        bundle.putString("thumbnail", movieData.getmThumb());


        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.detail_viewer, fragment).commit();


    }




}





