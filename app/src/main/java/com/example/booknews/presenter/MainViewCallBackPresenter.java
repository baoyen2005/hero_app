package com.example.booknews.presenter;

import android.util.Log;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.booknews.model.HeroModel;
import com.example.booknews.model.entity.Hero;
import com.example.booknews.presenter.inteface.MainActivityContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainViewCallBackPresenter implements MainActivityContract.CallBackPresenter, MainActivityContract.APIListener,
        MainActivityContract.CallBackSearchHeroes, MainActivityContract.CallBackSliderModel {
    private final String TAG = "MainViewPresenter";
    private final MainActivityContract.CallBackView callBackView;
    private final MainActivityContract.CallBackModel callBackModel;

    public MainViewCallBackPresenter(MainActivityContract.CallBackView callBackView) {
        this.callBackView = callBackView;
        callBackModel = new HeroModel();
        getHeroes();
        this.callBackView.setupUI();
        this.callBackView.handleEvent();

    }

    @Override
    public void getHeroes() {
        callBackView.showProgressDialog();
        callBackModel.getHeroes(this);
    }

    @Override
    public void getHeroesByName(String name, MainActivityContract.CallBackSearchHeroes listener) {
        callBackView.showProgressDialog();
        callBackModel.getHeroesByName(name, this);
    }

    @Override
    public void getSlideModels() {
        callBackModel.getSlideModels(this);
    }

    @Override
    public void onSuccess(Response<List<Hero>> response) {
        if (response != null && response.isSuccessful()) {
            callBackView.hideProgressDialog();
            callBackView.displayHeroData(response.body());
        } else {
            callBackView.showMessage("Data is null");
        }
    }

    @Override
    public void onError(Response<List<Hero>> response) {
        callBackView.hideProgressDialog();
        callBackView.showMessage("Error Occurred.");
    }

    @Override
    public void onFailure(Throwable t) {
        callBackView.hideProgressDialog();
        callBackView.showMessage(t.getMessage());
    }

    @Override
    public void onSuccessfullySearch(List<Hero> heroes) {
        Log.i(TAG, "onSuccessfullySearch: herolist + " + heroes.size());
        if (!heroes.isEmpty()) {
            callBackView.hideProgressDialog();
            callBackView.displayHeroData(heroes);
        } else {
            callBackView.showMessage("Heroes is empty.");
        }
    }

    @Override
    public void onFailureSearch(List<Hero> heroes) {
        if (heroes != null && !heroes.isEmpty()) {
            callBackView.hideProgressDialog();
            callBackView.displayHeroData(heroes);
        } else {
            callBackView.showMessage("Name is valid or name is blank");
        }
    }

    @Override
    public void onSuccess(ArrayList<SlideModel> slideModels) {
        if(slideModels != null &&  !slideModels.isEmpty()){
            callBackView.setSlider(slideModels);
        }
        else {
            callBackView.showMessage("slideModels is null or empty");
        }
    }

    @Override
    public void onError(String mes) {
        callBackView.showMessage(mes);
    }
}
