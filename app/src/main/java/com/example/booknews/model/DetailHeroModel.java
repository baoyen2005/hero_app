package com.example.booknews.model;

import com.example.booknews.model.entity.Hero;
import com.example.booknews.presenter.inteface.DetailActivityContract;

public class DetailHeroModel implements DetailActivityContract.CallBackDetailModel {


    @Override
    public void setHero(Hero hero, DetailActivityContract.CallBackSetHero callBackSetHero) {
        if(hero != null){
            callBackSetHero.onSuccess(hero);
        }
        else {
            callBackSetHero.onError("Hero is null");
        }
    }
}
