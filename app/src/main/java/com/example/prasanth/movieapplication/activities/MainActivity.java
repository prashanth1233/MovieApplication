package com.example.prasanth.movieapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.prasanth.movieapplication.CustomInterface;
import com.example.prasanth.movieapplication.moviemodels.DoctorStrange;
import com.example.prasanth.movieapplication.moviemodels.MoviesModel;
import com.example.prasanth.movieapplication.R;
import com.example.prasanth.movieapplication.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CustomInterface {

    private int moviesImages[] = {
            R.drawable.logan,
            R.drawable.findingdory,
            R.drawable.jurassicworld,
            R.drawable.doctorstrange,
            R.drawable.rings
    };

    private List moviesList = new ArrayList();
    private RecyclerView recyclerView;
    private int pos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layout);
        addDataToModel();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, moviesList, this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void addDataToModel() {

        for (int i = 0; i < moviesImages.length; i++) {
            MoviesModel moviesModel = new MoviesModel();
            moviesModel.setMovieImage(moviesImages[i]);
            moviesList.add(moviesModel);
        }

    }

    @Override
    public void onClick(View view) {
         pos = (int) view.getTag();
        AsyncTaskActivity asyncTaskActivity = new AsyncTaskActivity(this, this);
        asyncTaskActivity.execute(pos);


    }

    @Override
    public void result(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("data", text);
        bundle.putInt("position",pos);

        if(pos==0)
        {

            Intent intent=new Intent(this,TextActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
     else if(pos==1)
        {
           Intent intent=new Intent(this,FindDoryMovieActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(pos==2)
        {
          Intent intent=new Intent(this,JurassicWorldActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(pos==3)
        {
            Intent intent=new Intent(this,DoctorStrangeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(pos==4)
        {
            Intent intent=new Intent(this,RingsMovieActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else
        {

        }
    }

}
