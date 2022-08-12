package com.example.booknews.view.screen;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.booknews.R;
import com.example.booknews.model.entity.Hero;
import com.example.booknews.presenter.MainViewCallBackPresenter;
import com.example.booknews.presenter.inteface.MainActivityContract;
import com.example.booknews.view.adapter.HeroMainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private HeroMainAdapter adapter;
    private SearchView searchView;
    private ImageSlider imageSlider;
    private ProgressDialog progressDialog;

    private MainActivityContract.CallBackPresenter callBackPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        progressDialog = new ProgressDialog(this);
        adapter = new HeroMainAdapter(new AdapterItemEvent());
        callBackPresenter = new MainViewCallBackPresenter(new EventView(MainActivity.this));
        Handler handler = new Handler();
        handler.postDelayed(() -> callBackPresenter.getSlideModels(), 5000);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.searchView);
        imageSlider = findViewById(R.id.imageSlider);
    }

    private class EventView implements MainActivityContract.CallBackView {
        private final Context context;

        public EventView(Context context) {
            this.context = context;
        }

        @Override
        public void setupUI() {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void handleEvent() {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchHeroByName(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    searchHeroByName(newText);
                    return false;
                }
            });
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void displayHeroData(@NonNull List<Hero> heroes) {
            if (!heroes.isEmpty()) {
                Log.d(TAG, "displayHeroData: "+ heroes.size());
                adapter.updateList(heroes);
            } else {
                Log.d(TAG, "displayHeroData: fail ");
                showMessage("data is empty");
            }
        }

        @Override
        public void setSlider(ArrayList<SlideModel> slideModels) {
            imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        }

        @Override
        public void showMessage(String msg) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void showProgressDialog() {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.setMessage("Loading...");
            } else {
                assert progressDialog != null;
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                try {
                    progressDialog.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        @Override
        public void hideProgressDialog() {
            try {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog.hide();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class AdapterItemEvent implements HeroMainAdapter.ClickShowDetailHero {
        @Override
        public void openDetailHero(Hero hero, int position) {
            DetailActivity.started(MainActivity.this, hero);
        }
    }

    private void searchHeroByName(String query) {
        callBackPresenter.getHeroesByName(query, new MainActivityContract.CallBackSearchHeroes() {
            @Override
            public void onSuccessfullySearch(List<Hero> list) {
                if (list.size() == 0) {
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailureSearch(List<Hero> heroList) {
            }
        });
    }
}