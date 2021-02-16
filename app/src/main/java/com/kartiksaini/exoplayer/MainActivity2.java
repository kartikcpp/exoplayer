package com.kartiksaini.exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {
    String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=aa9ea67961cacbb7c13d8eb5d2163a8e";
    JSONObject myjsonobject;
    TextView mytextview;
    RequestQueue queue ;
    private ProgressBar mprogressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mprogressbar=findViewById(R.id.progressbar);
        queue=Volley.newRequestQueue(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
            mynetworkrequest();


    }

    private void mynetworkrequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mprogressbar.setVisibility(View.GONE);
                        myjsonobject=response;
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        queue.add(jsonObjectRequest);

    }
}