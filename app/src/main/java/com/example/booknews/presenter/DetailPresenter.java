package com.example.booknews.presenter;

import com.example.booknews.model.DetailHeroModel;
import com.example.booknews.model.entity.Hero;
import com.example.booknews.presenter.inteface.DetailActivityContract;

public class DetailPresenter implements DetailActivityContract.CallBackDetailPresenter,
        DetailActivityContract.CallBackSetHero {
    private final DetailActivityContract.CallBackDetailView callBackView;
    private final DetailActivityContract.CallBackDetailModel callBackModel;
    private Hero hero;

    public DetailPresenter(DetailActivityContract.CallBackDetailView callBackView, Hero hero) {
        this.hero = hero;
        this.callBackView = callBackView;
        callBackModel = new DetailHeroModel();
        getHero();
    }

    @Override
    public void getHero() {
        callBackModel.setHero(hero, this);
    }

    @Override
    public void onSuccess(Hero hero) {
        if (hero != null) {
            callBackView.setUpValueForView(hero);
        }
    }

    @Override
    public void onError(String mes) {
        callBackView.showMessage(mes);
    }
}
