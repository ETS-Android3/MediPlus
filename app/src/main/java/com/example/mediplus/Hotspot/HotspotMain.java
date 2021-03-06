package com.example.mediplus.Hotspot;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mediplus.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HotspotMain extends AppCompatActivity {

    RecyclerView recyclerView;
    List<HotspotModel> hotspotsList;
    HotspotListAdapter adapter;
    private ProgressBar spinner;

    private static String URL_HOTSPOTS = "https://keralastats.coronasafe.live/hotspots.json";
//    Button refreshBtn;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.example_menu,menu);
        //    MenuItem aboutItem = menu.findItem(R.id.action_about);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot_main);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.myrecycleview);
        hotspotsList = new ArrayList<>();
//        refreshBtn = findViewById(R.id.refresh_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);



//        refreshBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
        ProgressBar progressBar = new ProgressBar(getApplicationContext());
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_HOTSPOTS, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray hotspotarray = null;
                        try {
                            hotspotarray = response.getJSONArray("hotspots");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < hotspotarray.length(); i++) {
                            HotspotModel hotspots = new HotspotModel();
                            try {
                                JSONObject hotspotobject = hotspotarray.getJSONObject(i);

                                hotspots.setWards(hotspotobject.getString("wards"));
                                hotspots.setDistrict(hotspotobject.getString("district"));
                                hotspots.setLsgd(hotspotobject.getString("lsgd"));

                                hotspotsList.add(hotspots);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        adapter = new HotspotListAdapter(HotspotMain.this,hotspotsList);
                        spinner.setVisibility(View.GONE);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", "onErrorResponse: " + error.getMessage() );
                    }
                });

                queue.add(jsonObjectRequest);



//                    }
//                });


    }
}