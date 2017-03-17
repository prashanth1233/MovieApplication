package com.example.prasanth.movieapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prasanth.movieapplication.R;
import com.example.prasanth.movieapplication.moviemodels.MovieModels;
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

public class TextActivity extends AppCompatActivity {

    private String finaldata;
    TextView movieTitle,descr,releaseDate,popularity;
    ImageView image;
    MovieModels movieModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        Bundle bundle = getIntent().getExtras();

            movieTitle   = (TextView) findViewById(R.id.movieTitle);
            descr = (TextView) findViewById(R.id.subTitle);
            releaseDate = (TextView) findViewById(R.id.releaseDate);
            popularity = (TextView) findViewById(R.id.popularity);
            image = (ImageView) findViewById(R.id.movieImage);

        try {
            if (bundle != null) {
                finaldata = bundle.getString("data");
                JSONObject jsonObject = new JSONObject(finaldata);
                movieModels = jsonParser(jsonObject, MovieModels.class);
                String title = movieModels.title.toString();
                String movieDescription = movieModels.overview.toString();
                String moviePopularity = movieModels.popularity.toString();
                String url = "https://image.tmdb.org/t/p/w500/7AGV84ZcdvoZNpxqjiktti8TXLO.jpg";
                String date = movieModels.releaseDate.toString();
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

