package com.example.booknews.view.screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booknews.R;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;

    private Animation topAnim, bottomAnim;
    private ImageView imgBgSplashScreen;
    private TextView txtTittleSplash,txtContentSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        imgBgSplashScreen = findViewById(R.id.imgBgSplashScreen);
        txtTittleSplash = findViewById(R.id.txtTittleSplash);
        txtContentSplash = findViewById(R.id.txtContentSplash);

        imgBgSplashScreen.setAnimation(topAnim);
        txtTittleSplash.setAnimation(bottomAnim);
        txtContentSplash.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}