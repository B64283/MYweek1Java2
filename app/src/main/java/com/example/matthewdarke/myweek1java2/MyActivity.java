package com.example.matthewdarke.myweek1java2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.matthewdarke.myweek1java2.Fragments.DetailFragment;
import com.example.matthewdarke.myweek1java2.Fragments.MasterFragment;
import com.example.matthewdarke.myweek1java2.Model.MovieData;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity implements MasterFragment.OnListCallBack {

    // Rotten Tomatoes API key for application
    private static final String API_KEY = "uwagqpf95hwhwe4vnfe36gj7";


    protected String SEARCH_WORD = "Incredible";
    //number of movies in a single request to web server
    private static final int PAGE_LIMIT = 10;

    public String detailSTR;
    List<MovieData> movieDataList;
    // private Button searchButton;
    private ArrayList<MovieData> mMoviesList;

    private static final String TAG_MOVIE_TITLE = "title";
    private static final String TAG_MOVIE_YEAR = "year";
    private static final String TAG_MOVIE_RATING = "mpaa_rating";
    private static final String TAG_MOVIE_RUN = "runtime";
    //private String id;
    public JSONObject movies;




    @Override
    protected void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_my);
        if(SavedInstanceState == null) {

            MasterFragment masterFragment = new MasterFragment();
            getFragmentManager().beginTransaction().add(R.id.master_container,masterFragment).commit();
}
//set up detail view with fragment manager
        DetailFragment detailFragment = new DetailFragment();

        getFragmentManager().beginTransaction().add(R.id.detail_viewer,detailFragment)
        .commit();

        mMoviesList = new ArrayList<MovieData>();


        new MyTask().execute();

        //searchButton = (Button) findViewById(R.id.button_search);
        //searchButton.setOnClickListener(new OnClickListener()




            // sends an API request when the button is pressed





            //@Override
            //public void onClick(View arg0)
            //{
                //new MyTask().execute("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + SEARCH_WORD + "&page_limit=" + PAGE_LIMIT);
            //}
       // });
        //moviesList = (ListView) findViewById(R.id.list);
 }

    //private void refreshMoviesList(String[] movieTitles)
   // {
        //pb = (ProgressBar) findViewById(R.id.progressBar2);
       // pb.setVisibility(View.INVISIBLE);
       //moviesList.(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieTitles));
   // }


    //interface passes data
    @Override
    public void onItemSelected(String detailStr) {

        detailSTR = detailStr;
        Log.i("List Item licked on", detailStr);

    //Bundle b = movieData.toBundle();



    }


//inerface callback



    //set up asyncTask method
    private class MyTask extends AsyncTask<String, String, String>
    {
        // makes request to specified url
        @Override
        protected String doInBackground(String... uri)
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try
            {
                // make a HTTP request

                response = httpclient.execute(new HttpGet("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + SEARCH_WORD + "&page_limit=" + PAGE_LIMIT));

                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK)
                {
                    // request successful - read the response and close the connection
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    responseString = out.toString();
                }
                else
                {
                    // request failed - close the connection
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            }
            catch (Exception e)
            {
                Log.d("Test", "Unsuccessful request!");
            }
            return responseString;
        }





        // if the request above completed successfully, this method will
        // automatically run so we can do something with the response

        @Override
        protected void onPostExecute(String response)
        {
            super.onPostExecute(response);

            if (response != null) {
                try {
                    // converts String response to a JSON object,
                    // JSON is the response format Rotten Tomatoes uses
                    JSONObject jsonResponse = new JSONObject(response);

                    // fetch the array of movies in the response
                    JSONArray ar = jsonResponse.getJSONArray("movies");

                    // add each movie's title to an array
                    //String[] movieTitles = new String[movies.length()];


                    //JSONArray ar = new JSONArray(response);
                    ArrayList<MovieData> mMoviesList = new ArrayList<MovieData>();

                    for (int i = 0; i < ar.length(); i++) {

                        JSONObject obj = ar.getJSONObject(i);
                        MovieData mData = new MovieData();

                        mData.setmTitle(obj.getString("title"));
                        mData.setmYear(obj.getString("year"));
                        mData.setmRate(obj.getString("mpaa_rating"));
                        mData.setmRunT(obj.getString("runtime"));

                        mMoviesList.add(mData);


                    }




                    // update the UI and make pb invisible
                    //moviesList = (ListView) findViewById(R.id.master_container);
                    //refreshMoviesList();
                    //pb.setVisibility(View.INVISIBLE);
                } catch (JSONException e) {
                    Log.d("Test", "Failed to parse the JSON response!");
                }
            }
        }
    }
}