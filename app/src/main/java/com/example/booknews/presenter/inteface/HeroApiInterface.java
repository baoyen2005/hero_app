package com.example.booknews.presenter.inteface;


import com.example.booknews.model.entity.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HeroApiInterface {
    // api1 : https://simplifiedcoding.net/demos/marvel
    // api2 :https://howtodoandroid.com/movielist.json
    // String BASE_URL 1= "https://howtodoandroid.com/";

    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Hero>> getAllHero();
}
