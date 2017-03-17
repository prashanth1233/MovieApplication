package com.example.prasanth.movieapplication.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;

import com.example.prasanth.movieapplication.CustomInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class AsyncTaskActivity extends AsyncTask<Integer,String,String> {
    private ProgressDialog progressDialog;

private  HttpURLConnection httpURLConnection;
private CustomInterface customInterface;
    private Context context;


    public AsyncTaskActivity(Context context, CustomInterface customInterface) {
        this.customInterface = customInterface;
        this.context=context;
    }

    public void onPreExecute()
    {
         progressDialog= ProgressDialog.show(context,"Fetching Data","Please Wait");
    }

    @Override
    protected String doInBackground(Integer...position) {


       if(position[0]>=0)
       {
           String data=getJsonData();
           return data;
       }
        else {
            return null;
        }

    }

    public String getJsonData()
    {
        try
        {
            URL url=new URL("https://api.themoviedb.org/3/movie/550?api_key=96c3521ea79ac1bdd0068031696ab49f");
           httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer=new StringBuffer();
            String line="";
            while((line=bufferedReader.readLine())!=null)
            {
              stringBuffer.append(line+"\n");
            }
            return stringBuffer.toString();
        }

        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
       finally {
            if(httpURLConnection!=null)
            {
                httpURLConnection.disconnect();
            }
        }
        return null;
    }

    public void onPostExecute(String datas)
    {
        progressDialog.dismiss();
     customInterface.result(datas);
    }



}
