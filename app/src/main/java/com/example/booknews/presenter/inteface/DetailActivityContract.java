package com.example.booknews.presenter.inteface;

import com.example.booknews.model.entity.Hero;

public interface DetailActivityContract {
    interface CallBackDetailModel{
        void setHero(Hero hero, CallBackSetHero callBackSetHero);
    }
    interface CallBackDetailView {
        void setUpValueForView(Hero hero);

        void showMessage(String msg);
    }

    interface CallBackDetailPresenter {
        void getHero();
    }

    interface CallBackSetHero {
        void onSuccess(Hero hero);

        void onError(String mes);
    }
}
