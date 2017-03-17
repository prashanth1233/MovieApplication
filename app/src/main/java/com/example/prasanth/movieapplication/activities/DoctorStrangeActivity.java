package com.example.prasanth.movieapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prasanth.movieapplication.R;
import com.example.prasanth.movieapplication.moviemodels.Result2;
import com.example.prasanth.movieapplication.moviemodels.Result3;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorStrangeActivity extends AppCompatActivity {


    private String finaldata;
    TextView movieTitle,descr,releaseDate,popularity;
    ImageView image;
    Result3 doctorStrange;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_strange);
        Bundle bundle = getIntent().getExtras();

        movieTitle  = (TextView) findViewById(R.id.movieTitle);
        descr = (TextView) findViewById(R.id.subTitle);
        releaseDate = (TextView) findViewById(R.id.releaseDate);
        popularity = (TextView) findViewById(R.id.popularity);
        image = (ImageView) findViewById(R.id.movieImage);

        try {
            if (bundle != null) {
                finaldata = bundle.getString("data");
                JSONObject jsonObject = new JSONObject(finaldata);
                doctorStrange = jsonParser(jsonObject, Result3.class);
                String title = doctorStrange.title.toString();
                String movieDescription = doctorStrange.overview.toString();
                String moviePopularity = doctorStrange.popularity.toString();
                String url = "https://image.tmdb.org/t/p/w500/cmQqH13qomKapTO5tlXCN1H9JFg.jpg";
                String date = doctorStrange.releaseDate.toString();
                movieTitle.setText(title);
                popularity.setText(moviePopularity);
                descr.setText(movieDescription);
                releaseDate.setText(date);
                Picasso.with(this).load(url).into(image);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public static <T extends Object> T jsonParser(JSONObject response, Class<T> parserModel) {
        Gson gson = new Gson();
        Reader reader = null;
        try {
            InputStream result = new ByteArrayInputStream(response.toString().getBytes(Charset.forName("UTF-8")));
            reader = new InputStreamReader(result);
        } catch (Exception e) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, e);
        }
        return gson.fromJson(reader, parserModel);
    }

}
