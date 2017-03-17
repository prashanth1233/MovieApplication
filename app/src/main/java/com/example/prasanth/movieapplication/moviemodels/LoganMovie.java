
package com.example.prasanth.movieapplication.moviemodels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoganMovie {

    @SerializedName("page")

    public Integer page;
    @SerializedName("results")

    public List<com.example.prasanth.movieapplication.moviemodels.Result> results = null;
    @SerializedName("total_results")

    public Integer totalResults;
    @SerializedName("total_pages")

    public Integer totalPages;

}
