package com.example.geoff.ytandroidproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.geoff.ytandroidproject.Adapters.YoutubeAdapter;
import com.example.geoff.ytandroidproject.R;
import com.example.geoff.ytandroidproject.models.YoutubeResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.widget.SearchView youtubeSearch = (android.widget.SearchView) this.findViewById(R.id.Search);


        youtubeSearch.setActivated(true);
        youtubeSearch.setQueryHint("Youtube search");
        youtubeSearch.onActionViewExpanded();
        youtubeSearch.clearFocus();
        youtubeSearch.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit (String query) {
                boolean success = searchOnYoutube(query);

                return success;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return false;
            }
        });
    }






    protected void fillResults(String jsonResponse) throws JSONException {

        JSONObject json = new JSONObject(jsonResponse);
        JSONArray items = json.getJSONArray("items");

        final List<YoutubeResult> YoutubeResultsListView = new ArrayList<YoutubeResult>();

        for (int i = 0; i < items.length(); i++) {
            JSONObject YoutubeResultObject = items.getJSONObject(i);
            JSONObject YoutubeResultSnippet = YoutubeResultObject.getJSONObject("snippet");
            JSONObject YoutubeResultId = YoutubeResultObject.getJSONObject("id");
            String id = YoutubeResultId.getString("videoId");
            String title = YoutubeResultSnippet.getString("title");
            String date = YoutubeResultSnippet.getString("publishedAt").substring(0, 10);
            String author = YoutubeResultSnippet.getString("channelTitle");
            String description = YoutubeResultSnippet.getString("description");

            String thumbnails = YoutubeResultSnippet.getJSONObject("thumbnails").getJSONObject("high").getString("url");

            YoutubeResultsListView.add(new YoutubeResult(title, description, thumbnails, id, date, author));


        }

        YoutubeAdapter adapter = new YoutubeAdapter(MainActivity.this, YoutubeResultsListView);

        ListView youtubeList = (ListView) this.findViewById(R.id.listResults);
        youtubeList.setAdapter(adapter);

        // COMMENTED BECAUSE ClickListener DOES NOT WORK
//        youtubeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                YoutubeResult youtubeResult = YoutubeResultsListView.get(position);
//                PlayerActivity.start(MainActivity.this, youtubeResult);
//            }
//        });

    }

    protected boolean searchOnYoutube(String query) {
        //System.out.println(query);

        String requestAPI = "https://www.googleapis.com/youtube/v3/search";
        String partAPI = "?part=id,snippet";
        String queryAPI = "&q=" + query.replace(' ', '+');
        String typeAPI = "&type=video";
        String keyAPI = "&key=AIzaSyDNTq3zr5ybzk4LU-8_MeonESjkfEW9gVw";

        String url = requestAPI + partAPI + queryAPI + typeAPI + keyAPI;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);
                        try {
                            fillResults(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);

        return false;
    }
}