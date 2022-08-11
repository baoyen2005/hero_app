package com.example.booknews.presenter;

import android.util.Log;

import com.example.booknews.model.HeroModel;
import com.example.booknews.model.entity.Hero;
import com.example.booknews.presenter.inteface.MainActivityContract;

import java.util.List;

import retrofit2.Response;

public class MainViewPresenter implements MainActivityContract.Presenter, MainActivityContract.APIListener ,
                                MainActivityContract.SearchListHeroListener
{
    private final String TAG = "MainViewPresenter";
    private MainActivityContract.View mView;
    private MainActivityContract.Model mModel;


    public MainViewPresenter(MainActivityContract.View view) {

        mView = view;
        mModel = new HeroModel();
        mView.setupUI();
        mView.handleEvent();
        getHeroes();
    }


    @Override
    public void getHeroes() {
        mView.showProgressDialog();
        mModel.getBooks(this);
    }

    @Override
    public void getHeroesByName(String name, MainActivityContract.SearchListHeroListener listener) {
        mView.showProgressDialog();
        mModel.getBooksByName(name, this);
    }


    @Override
    public void onSuccess(Response<List<Hero>> response) {
        Log.d("mvp", response.body().size() + "");
        mView.hideProgressDialog();
        mView.displayHeroData(response.body());
    }

    @Override
    public void onError(Response<List<Hero>> response) {
        mView.hideProgressDialog();
        mView.showMessage("Error Occured.");
    }

    @Override
    public void onFailure(Throwable t) {
        mView.hideProgressDialog();
        mView.showMessage(t.getMessage());
    }

    @Override
    public void onSuccessfullySearch(List<Hero> heroList) {
        Log.i(TAG, "onSuccessfullySearch: herolist + "+heroList.size());
        mView.hideProgressDialog();
        mView.displayHeroData(heroList);

    }

    @Override
    public void onFailureSearch(List<Hero> heroList) {
        mView.hideProgressDialog();
        mView.displayHeroData(heroList);
        mView.showMessage("Name is valid or name is blank");

    }
}
