package com.example.booknews.presenter;

import android.util.Log;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.booknews.model.MainModel;
import com.example.booknews.model.entity.Hero;
import com.example.booknews.presenter.inteface.MainActivityContract;
import com.example.booknews.utils.FunctionUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainPresenter implements MainActivityContract.CallBackMainPresenter {
    private final String TAG = "MainViewPresenter";
    private final MainActivityContract.CallBackView callBackView;
    private final MainActivityContract.CallBackModel callBackModel;

    public MainPresenter(MainActivityContract.CallBackView callBackView) {
        this.callBackView = callBackView;
        callBackModel = new MainModel();
        getHeroesInPresenter();
    }

    @Override
    public void getHeroesInPresenter() {
        callBackView.showProgressDialogInView();
        callBackModel.getHeroesByGetApiInModel(this);
    }

    @Override
    public void getHeroesSuccessInPresenter(Response<List<Hero>> response) {
        if (response != null && response.body() != null && !response.body().isEmpty()) {
            callBackView.hideProgressDialogInView();
            callBackView.displayHeroDataInView(response.body());
        } else {
            callBackView.hideProgressDialogInView();
            callBackView.showMessageInView(FunctionUtils.getDataIsEmpty);
        }
    }

    @Override
    public void getHeroesErrorInPresenter(String mes) {
        callBackView.hideProgressDialogInView();
        callBackView.showMessageInView(FunctionUtils.getDataIsEmpty);
    }

    @Override
    public void getHeroesOnFailureInPresenter(Throwable t) {
        callBackView.hideProgressDialogInView();
        callBackView.showMessageInView(t.getMessage());
    }

    @Override
    public void getHeroesByNameInPresenter(String name) {
        Log.d(TAG, "getHeroesByNameInPresenter: " + name);
        callBackModel.getHeroesByNameInModel(name, this);
    }

    @Override
    public void onSuccessfullySearchInPresenter(List<Hero> heroes) {
        callBackView.displayHeroDataInView(heroes);
    }

    @Override
    public void onFailureSearchInPresenter(List<Hero> heroes) {
        callBackView.displayHeroDataInView(heroes);
    }

    @Override
    public void getSlideModelInPresenter() {
        callBackModel.getSlideModelsInModel(this);
    }

    @Override
    public void getSlideModelSuccessInPresenter(ArrayList<SlideModel> slideModels) {
        if (slideModels != null && !slideModels.isEmpty()) {
            Log.d(TAG, "onSuccess: SlideModel " + slideModels.size());
            callBackView.showSliderInView(slideModels);
        } else {
            callBackView.showMessageInView(FunctionUtils.sliderModelError);
        }
    }

    @Override
    public void getSlideModelFailInPresenter(String mes) {
        callBackView.showMessageInView(mes);
    }
}
