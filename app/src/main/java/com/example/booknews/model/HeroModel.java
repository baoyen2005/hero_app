package com.example.booknews.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.booknews.model.entity.Hero;
import com.example.booknews.model.network.RetrofitClass;
import com.example.booknews.presenter.inteface.HeroApiInterface;
import com.example.booknews.presenter.inteface.MainActivityContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroModel implements MainActivityContract.Model {
    private final String TAG = "HeroModel";
    private final List<Hero> heroList = new ArrayList<>();
    private final List<Hero> list = new ArrayList<>();

    @Override
    public void getBooks(MainActivityContract.APIListener listener) {
        try {

            // 1. Create a Retrofit client and using the interface make the call

            HeroApiInterface service = RetrofitClass.getInstance().create(HeroApiInterface.class);

            Call<List<Hero>> call = service.getAllHero();
            call.enqueue(new Callback<List<Hero>>() {
                @Override
                public void onResponse(@NonNull Call<List<Hero>> call, @NonNull Response<List<Hero>> response) {

                    if (response.isSuccessful()) {
                        listener.onSuccess(response);
                        assert response.body() != null;
                        heroList.addAll(response.body());
                    } else {
                        listener.onError(response);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<Hero>> call, Throwable t) {

                    listener.onFailure(t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getBooksByName(String name, MainActivityContract.SearchListHeroListener listener) {
        Log.i(TAG, "getBooksByName: name = " + name);

        if (name == null || name.isEmpty()) {
            listener.onFailureSearch(heroList);
        }
        else {
            list.clear();
            for (int i = 0; i < heroList.size(); i++) {
                if (heroList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                    list.add(heroList.get(i));
                }
            }
            listener.onSuccessfullySearch(list);
            Log.i(TAG, "getBooksByName: list.size "+ list.size());
        }
    }
}
