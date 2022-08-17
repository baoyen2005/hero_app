package com.example.booknews.presenter;

import android.util.Log;

import com.example.booknews.model.entity.Hero;
import com.example.booknews.presenter.inteface.DetailActivityContract;
import com.example.booknews.utils.FunctionUtils;

public class DetailPresenter implements DetailActivityContract.CallBackDetailPresenter {
    private final String TAG = "DetailPresenter";
    private final DetailActivityContract.CallBackDetailView callBackView;

    public DetailPresenter(DetailActivityContract.CallBackDetailView callBackView) {
        this.callBackView = callBackView;
    }

    @Override
    public void getHeroInPresenter(Hero hero) {
        if (hero != null) {
            getHeroSuccessInPresenter(hero);
        } else {
            getHeroFailInPresenter(FunctionUtils.getHeroFromBundleFail);
        }
    }

    @Override
    public void getHeroSuccessInPresenter(Hero hero) {
        Log.d(TAG, "getHeroSuccess: " + hero.getImageurl());
        callBackView.setUpValueForView(hero);
    }

    @Override
    public void getHeroFailInPresenter(String mes) {
        Log.d(TAG, "getHeroFail: ");
        callBackView.showMessageInView(mes);
    }
}
