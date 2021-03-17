package com.example.mediplus.Covid.user.news;

import com.example.mediplus.Covid.user.news.model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<Headlines> getSpecificData(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<Headlines> getCoronaData(
            @Query("country") String country,
            @Query("q") String query,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<Headlines> getSpecificCoronaData(
            @Query("country") String country,
            @Query("q") String query1,
            @Query("q") String query2,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );

}
