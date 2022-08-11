package com.example.booknews.view.screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.booknews.R;
import com.example.booknews.databinding.ActivityDetailBinding;
import com.example.booknews.model.entity.Hero;
import com.example.booknews.presenter.DetailPresenter;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = "DetailActivity";

    private ActivityDetailBinding binding;
    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        Bundle bundle = getIntent().getBundleExtra("bundle_hero");
        detailPresenter = new DetailPresenter();
        detailPresenter.setHero ((Hero)bundle.getSerializable("hero"));
        setValueForView();

    }

    @SuppressLint("SetTextI18n")
    private void setValueForView() {
        Glide.with(this).load(detailPresenter.getHero().getImageurl()).into(binding.imgDetailBackground);
        binding.txtBio.setText(detailPresenter.getHero().getBio());
        binding.txtCreateBy.setText( detailPresenter.getHero().getCreatedby());
        binding.txtPublisher.setText(detailPresenter.getHero().getPublisher());
        binding.txtFirstAppearance.setText("First Appearance: "+ detailPresenter.getHero().getFirstappearance());
        binding.txtName.setText(detailPresenter.getHero().getName());
        binding.txtRealName.setText("Real name: "+ detailPresenter.getHero().getRealname());
        binding.txtTeam.setText(" (Team: "+ detailPresenter.getHero().getTeam()+")");
        binding.imgBackToScreen.setOnClickListener(view -> {
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}