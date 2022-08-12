package com.example.booknews.presenter.inteface;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.booknews.model.entity.Hero;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public interface MainActivityContract {
    interface CallBackModel {
        void getHeroes(final APIListener listener);

        void getHeroesByName(String name, CallBackSearchHeroes listener);

        void getSlideModels(CallBackSliderModel callBackSliderModel);

    }

    interface CallBackView {
        void setupUI();

        void handleEvent();

        void displayHeroData(List<Hero> heroes);

        void setSlider(ArrayList<SlideModel> slideModels);

        void showMessage(String msg);

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface CallBackPresenter {
        void getHeroes();

        void getHeroesByName(String name, CallBackSearchHeroes listener);

        void getSlideModels();
    }

    interface APIListener {
        void onSuccess(Response<List<Hero>> response);

        void onError(Response<List<Hero>> response);

        void onFailure(Throwable t);
    }

    interface CallBackSearchHeroes {
        void onSuccessfullySearch(List<Hero> heroes);

        void onFailureSearch(List<Hero> heroes);
    }

    interface CallBackSliderModel{
        void onSuccess(ArrayList<SlideModel> getSlideModels);
        void onError(String mes);
    }
}
