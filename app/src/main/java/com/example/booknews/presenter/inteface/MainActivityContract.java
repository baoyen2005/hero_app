package com.example.booknews.presenter.inteface;

import com.example.booknews.model.entity.Hero;

import java.util.List;

import retrofit2.Response;

public interface MainActivityContract {
    interface Model {
        void getBooks(final APIListener listener);	// Retrieve list of movies
        void getBooksByName(String name,SearchListHeroListener listener);
    }

    interface View {

        void setupUI();
        void handleEvent();
        void displayHeroData(List<Hero> heroesList);
        void showMessage(String msg);	// To display message as Toast messages

        // Show and hide progress dialog
        void showProgressDialog();
        void hideProgressDialog();
    }

    interface Presenter {
        void getHeroes();
        void getHeroesByName(String name, SearchListHeroListener listener);
    }

    interface APIListener {

        void onSuccess(Response<List<Hero>> response);
        void onError(Response<List<Hero>> response);
        void onFailure(Throwable t);
    }

    interface SearchListHeroListener {

        void onSuccessfullySearch(List<Hero> heroList);
        void onFailureSearch(List<Hero> heroList);
    }

}
