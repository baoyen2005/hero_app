package com.example.booknews.view.screen;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

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
import com.example.booknews.presenter.MainPresenter;
import com.example.booknews.presenter.inteface.MainActivityContract;
import com.example.booknews.utils.FunctionUtils;
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

    private MainActivityContract.CallBackMainPresenter callBackMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.searchView);
        imageSlider = findViewById(R.id.imageSlider);
        /** init progress */
        progressDialog = new ProgressDialog(this);
        /** init adapter */
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new HeroMainAdapter(new AdapterItemEvent());
        recyclerView.setAdapter(adapter);
        /**  init presenter */
        callBackMainPresenter = new MainPresenter(new EventView(this));
        /**  init slider */
        Handler handler = new Handler();
        handler.postDelayed(() -> callBackMainPresenter.getSlideModelInPresenter(), 5000);
        /**  init event  */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange: ");
                callBackMainPresenter.getHeroesByNameInPresenter(newText);
                return false;
            }
        });
    }

    private class EventView implements MainActivityContract.CallBackView {
        private final Context context;

        public EventView(Context context) {
            this.context = context;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void displayHeroDataInView(@NonNull List<Hero> heroes) {
            if (!heroes.isEmpty()) {
                Log.d(TAG, "displayHeroData: " + heroes.size());
                recyclerView.setVisibility(View.VISIBLE);
                adapter.updateList(heroes);
            } else {
                Log.d(TAG, "displayHeroData: fail ");
                recyclerView.setVisibility(View.GONE);
                showMessageInView(FunctionUtils.getDataIsEmpty);
            }
        }

        @Override
        public void showSliderInView(ArrayList<SlideModel> slideModels) {
            imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        }

        @Override
        public void showMessageInView(String msg) {
            FunctionUtils.showToast(msg, context);
        }

        @Override
        public void showProgressDialogInView() {
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
        public void hideProgressDialogInView() {
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
}