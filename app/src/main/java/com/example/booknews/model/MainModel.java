package com.example.booknews.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.booknews.model.entity.Hero;
import com.example.booknews.model.network.RetrofitClass;
import com.example.booknews.presenter.inteface.HeroApiInterface;
import com.example.booknews.presenter.inteface.MainActivityContract;
import com.example.booknews.utils.FunctionUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel implements MainActivityContract.CallBackModel {
    private final String TAG = "HeroModel";
    private final List<Hero> heroes = new ArrayList<>();
    private final List<Hero> list = new ArrayList<>();
    private final ArrayList<SlideModel> slideModels = new ArrayList<>();

    @Override
    public void getHeroesByGetApiInModel(MainActivityContract.CallBackMainPresenter presenter) {
        try {
            HeroApiInterface service = RetrofitClass.getInstance().create(HeroApiInterface.class);
            Call<List<Hero>> call = service.getAllHero();
            call.enqueue(new Callback<List<Hero>>() {
                @Override
                public void onResponse(@NonNull Call<List<Hero>> call, @NonNull Response<List<Hero>> response) {
                    if (response.isSuccessful()) {
                        presenter.getHeroesSuccessInPresenter(response);
                        assert response.body() != null;
                        heroes.addAll(response.body());
                    } else {
                        presenter.getHeroesErrorInPresenter(FunctionUtils.getDataIsEmpty);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<Hero>> call, Throwable t) {
                    presenter.getHeroesOnFailureInPresenter(t);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getHeroesByNameInModel(String name, MainActivityContract.CallBackMainPresenter presenter) {
        if (name == null || name.isEmpty()) {
            presenter.onFailureSearchInPresenter(heroes);
        } else {
            list.clear();
            for (int i = 0; i < heroes.size(); i++) {
                if (heroes.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                    list.add(heroes.get(i));
                }
            }
            presenter.onSuccessfullySearchInPresenter(list);
        }
    }

    @Override
    public void getSlideModelsInModel(MainActivityContract.CallBackMainPresenter presenter) {
        if (!heroes.isEmpty()) {
            for (Hero hero : heroes) {
                slideModels.add(new SlideModel(hero.getImageurl(), ScaleTypes.FIT));
            }
            presenter.getSlideModelSuccessInPresenter(slideModels);
        } else {
            presenter.getSlideModelFailInPresenter(FunctionUtils.sliderModelError);
        }
    }
}
