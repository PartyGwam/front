package com.example.joe.depromeet_partygwam.Login.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Join.View.JoinActivity;
import com.example.joe.depromeet_partygwam.Login.Presenter.LoginContract;
import com.example.joe.depromeet_partygwam.Login.Presenter.LoginPresenter;
import com.example.joe.depromeet_partygwam.Main.View.PartyListActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        presenter = new LoginPresenter();
        presenter.attachView(this);
    }

    @OnClick(R.id.login_loginBtn)
    public void loginButtonClick() {
        startActivity(new Intent(LoginActivity.this, PartyListActivity.class));
        finish();
    }

    @OnClick(R.id.login_joinBtn)
    public void joinButtonClick() {
        startActivity(new Intent(LoginActivity.this, JoinActivity.class));
        finish();
    }

    @Override
    public void toast(String msg) {
        Runnable r = () -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        };
        new Thread(r).start();
    }

    @Override
    public void onSuccess(User user) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
