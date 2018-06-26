package com.example.joe.depromeet_partygwam.Login.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.joe.depromeet_partygwam.Join.View.JoinActivity;
import com.example.joe.depromeet_partygwam.Main.View.MainActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_loginBtn)
    public void loginButtonClick() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.login_joinBtn)
    public void joinButtonClick() {
        startActivity(new Intent(LoginActivity.this, JoinActivity.class));
        finish();
    }
}
