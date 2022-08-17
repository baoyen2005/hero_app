package com.example.booknews.presenter.inteface;

import com.example.booknews.model.entity.Hero;

public interface DetailActivityContract {
    interface CallBackDetailView {
        void setUpValueForView(Hero hero);

        void showMessageInView(String msg);
    }

    interface CallBackDetailPresenter {
        void getHeroInPresenter(Hero hero);

        void getHeroSuccessInPresenter(Hero hero);

        void getHeroFailInPresenter(String mes);
    }
}
