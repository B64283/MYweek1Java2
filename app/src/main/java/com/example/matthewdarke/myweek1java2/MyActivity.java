package com.example.matthewdarke.myweek1java2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.matthewdarke.myweek1java2.Fragments.DetailFragment;
import com.example.matthewdarke.myweek1java2.Fragments.MasterFragment;

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

public class MyActivity extends Activity implements MasterFragment.onItemClickListener
{
    // Rotten Tomatoes API key for application
    private static final String API_KEY = "uwagqpf95hwhwe4vnfe36gj7";

    //number of movies in a single request to web server
    private static final int PAGE_LIMIT = 10;
    public ProgressBar pb;
    private String SEARCH_WORD = "Incredible";
    private Button searchButton;
    private ListView moviesList;






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if(savedInstanceState == null) {

        MasterFragment fragment = new MasterFragment();
        getFragmentManager().beginTransaction()
            .replace(R.id.master_container, fragment, MasterFragment.TAG).commit();
}
//set up detail view with fragment manager

        FragmentManager manage = getFragmentManager();
        FragmentTransaction transact = manage.beginTransaction();

        //detail view

        DetailFragment deetFrag = new DetailFragment();
        transact.replace(R.id.detail_viewer, deetFrag, DetailFragment.TAG);
        transact.commit();

        new MyTask().execute();

        searchButton = (Button) findViewById(R.id.button_search);
        searchButton.setOnClickListener(new OnClickListener()



        {
            // sends an API request when the button is pressed





            @Override
            public void onClick(View arg0)
            {
                //new MyTask().execute("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + SEARCH_WORD + "&page_limit=" + PAGE_LIMIT);
            }
        });
        //moviesList = (ListView) findViewById(R.id.list);
    }

    private void refreshMoviesList(String[] movieTitles)
    {
        //pb = (ProgressBar) findViewById(R.id.progressBar2);
        pb.setVisibility(View.INVISIBLE);
        moviesList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieTitles));
    }


//inerface callback
    @Override
    public void displayDetails(String id) {

    }

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
                moviesList = (ListView) findViewById(R.id.master_container);
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

            if (response != null)
            {
                try
                {
                    // converts String response to a JSON object,
                    // JSON is the response format Rotten Tomatoes uses
                    JSONObject jsonResponse = new JSONObject(response);

                    // fetch the array of movies in the response
                    JSONArray movies = jsonResponse.getJSONArray("movies");

                    // add each movie's title to an array
                    String[] movieTitles = new String[movies.length()];
                    for (int i = 0; i < movies.length(); i++)
                    {
                        JSONObject movie = movies.getJSONObject(i);
                        movieTitles[i] = movie.getString("title");
                    }

                    // update the UI and make pb invisible

                    refreshMoviesList(movieTitles);
                    pb.setVisibility(View.INVISIBLE);
                }
                catch (JSONException e)
                {
                    Log.d("Test", "Failed to parse the JSON response!");
                }
            }
        }
    }
}