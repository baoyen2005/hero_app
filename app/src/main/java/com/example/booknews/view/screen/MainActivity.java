package com.example.booknews.view.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.booknews.R;
import com.example.booknews.model.entity.Hero;
import com.example.booknews.presenter.MainViewPresenter;
import com.example.booknews.presenter.inteface.MainActivityContract;

import com.example.booknews.view.adapter.HeroMainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, HeroMainAdapter.OnItemClickListener {
    private final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ImageSlider imageSlider;

    private ProgressDialog progressDialog;

    private HeroMainAdapter adapter;
    private MainActivityContract.Presenter mPresenter;

    private ArrayList<SlideModel> slideModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mPresenter = new MainViewPresenter(this);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.searchView);
        imageSlider = findViewById(R.id.imageSlider);
    }

    @Override
    public void setupUI() {
        progressDialog = new ProgressDialog(this);
        adapter = new HeroMainAdapter(getApplicationContext(), this);
        slideModels = new ArrayList<>();

        // init recycleView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
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

    private void searchHeroByName(String query) {
        mPresenter.getHeroesByName(query, new MainActivityContract.SearchListHeroListener() {
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

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void displayHeroData(List<Hero> heroesList) {
        Log.d("mvp", heroesList.size() + "");
        adapter.updateList(heroesList);
        // init slider
        for (Hero hero : heroesList) {
            slideModels.add(new SlideModel(hero.getImageurl(), ScaleTypes.FIT));

        }
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

    @Override
    public void onItemClick(Hero hero, int position) {
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("hero", hero);
        intent.putExtra("bundle_hero", bundle);
        startActivity(intent);
        finish();
    }
}