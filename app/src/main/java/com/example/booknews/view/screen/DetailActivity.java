package com.example.booknews.view.screen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.booknews.R;
import com.example.booknews.databinding.ActivityDetailBinding;
import com.example.booknews.model.entity.Hero;
import com.example.booknews.presenter.DetailPresenter;
import com.example.booknews.presenter.inteface.DetailActivityContract;
import com.example.booknews.utils.FunctionUtils;

public class DetailActivity extends AppCompatActivity {
    private final String TAG = "DetailActivity";
    private static final String KEY_BUNDLE = "hero";
    private static final String KEY_INTENT = "bundle_hero";

    private ActivityDetailBinding binding;

    public static void started(Context context, Hero hero) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_BUNDLE, hero);
        intent.putExtra(KEY_INTENT, bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        Bundle bundle = getIntent().getBundleExtra(KEY_INTENT);
        Hero hero = (Hero) bundle.getSerializable(KEY_BUNDLE);
        DetailPresenter detailPresenter = new DetailPresenter(new CallBackDetailPresenter(this));
        detailPresenter.getHeroInPresenter(hero);
    }

    private class CallBackDetailPresenter implements DetailActivityContract.CallBackDetailView {
        private final Context context;

        public CallBackDetailPresenter(Context context) {
            this.context = context;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void setUpValueForView(Hero hero) {
            Glide.with(context).load(hero.getImageurl()).into(binding.imgDetailBackground);
            binding.txtBio.setText(hero.getBio());
            binding.txtCreateBy.setText(hero.getCreatedby());
            binding.txtPublisher.setText(hero.getPublisher());
            binding.txtFirstAppearance.setText(R.string.first_appearance + hero.getFirstappearance());
            binding.txtName.setText(hero.getName());
            binding.txtRealName.setText(R.string.real_name + hero.getRealname());
            binding.txtTeam.setText(String.format(String.valueOf(R.string.team), hero.getTeam()));
            binding.imgBackToScreen.setOnClickListener(view -> finish());
        }

        @Override
        public void showMessageInView(String msg) {
            FunctionUtils.showToast(msg, context);
        }
    }
}