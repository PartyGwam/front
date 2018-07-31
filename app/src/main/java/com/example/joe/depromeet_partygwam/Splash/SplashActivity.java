package com.example.joe.depromeet_partygwam.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.Login.View.LoginActivity;
import com.example.joe.depromeet_partygwam.R;
import com.google.firebase.FirebaseApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.splash_button)
    ImageView button;
    @BindView(R.id.splash_text)
    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        FirebaseApp.initializeApp(this);
        ButterKnife.bind(this);
        animation();
    }

    @OnClick(R.id.splash_button)
    public void onButtonClick() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    private void animation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anmi);
        button.setAnimation(animation);
        text.setAnimation(animation);
    }
}
