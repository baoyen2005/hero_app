package com.example.booknews.presenter.inteface;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.booknews.model.entity.Hero;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public interface MainActivityContract {
    interface CallBackModel {
        void getHeroesByGetApiInModel(CallBackMainPresenter presenter);

        void getHeroesByNameInModel(String name, CallBackMainPresenter presenter);

        void getSlideModelsInModel(CallBackMainPresenter presenter);
    }

    interface CallBackView {
        void displayHeroDataInView(List<Hero> heroes);

        void showSliderInView(ArrayList<SlideModel> slideModels);

        void showMessageInView(String msg);

        void showProgressDialogInView();

        void hideProgressDialogInView();
    }

    interface CallBackMainPresenter {
        void getHeroesInPresenter();

        void getHeroesSuccessInPresenter(Response<List<Hero>> response);

        void getHeroesErrorInPresenter(String mes);

        void getHeroesOnFailureInPresenter(Throwable t);

        void getHeroesByNameInPresenter(String name);

        void onSuccessfullySearchInPresenter(List<Hero> heroes);

        void onFailureSearchInPresenter(List<Hero> heroes);

        void getSlideModelInPresenter();

        void getSlideModelSuccessInPresenter(ArrayList<SlideModel> slideModels);

        void getSlideModelFailInPresenter(String mes);
    }
}
