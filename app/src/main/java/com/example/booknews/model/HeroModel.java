package com.example.booknews.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.booknews.model.entity.Hero;
import com.example.booknews.model.network.RetrofitClass;
import com.example.booknews.presenter.inteface.HeroApiInterface;
import com.example.booknews.presenter.inteface.MainActivityContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroModel implements MainActivityContract.CallBackModel {
    private final String TAG ="HeroModel";
    private final List<Hero> heroes = new ArrayList<>();
    private final List<Hero> list = new ArrayList<>();
    private final ArrayList<SlideModel> slideModels = new ArrayList<>();

    @Override
    public void getHeroes(MainActivityContract.APIListener listener) {
        try {
            HeroApiInterface service = RetrofitClass.getInstance().create(HeroApiInterface.class);
            Call<List<Hero>> call = service.getAllHero();
            Log.d("heroModel", "getHeroes: call "+ call);
            call.enqueue(new Callback<List<Hero>>() {
                @Override
                public void onResponse(@NonNull Call<List<Hero>> call, @NonNull Response<List<Hero>> response) {
                    if (response.isSuccessful()) {
                        listener.onSuccess(response);
                        assert response.body() != null;
                        heroes.addAll(response.body());
                        Log.d(TAG, "onResponse: heroes "+heroes.size());
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
    public void getHeroesByName(String name, MainActivityContract.CallBackSearchHeroes listener) {
        Log.d(TAG, "getHeroesByName: heroes = "+ heroes.size());
        if (name == null || name.isEmpty()) {
            listener.onFailureSearch(heroes);
        }
        else {
            list.clear();
            for (int i = 0; i < heroes.size(); i++) {
                if (heroes.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                    list.add(heroes.get(i));
                }
            }
            listener.onSuccessfullySearch(list);
        }
    }

    @Override
    public void getSlideModels(MainActivityContract.CallBackSliderModel callBackSliderModel) {
        if(!heroes.isEmpty()){
            Log.d("HeroModel", "getSlideModels: " + heroes.size());
            for (Hero hero : heroes) {
                slideModels.add(new SlideModel(hero.getImageurl(), ScaleTypes.FIT));
            }
            callBackSliderModel.onSuccess(slideModels);
        }
        else{
            Log.d("HeroModel", "getSlideModels: is empty " + heroes);
            callBackSliderModel.onError("Heroes is empty. slideModels is fail");
        }
    }
}
