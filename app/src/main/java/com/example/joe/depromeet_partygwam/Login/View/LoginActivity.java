package com.example.joe.depromeet_partygwam.Login.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.LoginResponse;
import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Join.View.JoinActivity;
import com.example.joe.depromeet_partygwam.Login.Presenter.LoginContract;
import com.example.joe.depromeet_partygwam.Login.Presenter.LoginPresenter;
import com.example.joe.depromeet_partygwam.Main.View.MainActivity;
import com.example.joe.depromeet_partygwam.R;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.login_email)
    EditText editEmail;
    @BindView(R.id.login_password)
    EditText editPassword;
    @BindView(R.id.login_pb)
    ProgressBar pb;
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
        login();
    }

    @OnClick(R.id.login_loginBtn2)
    public void loginButton2Click() {
        login();
    }

    private void login() {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        if (email.equals("")) {
            toast("이메일을 입력해주세요.");
            return;
        }

        if (password.equals("")) {
            toast("비밀번호를 입력해주세요.");
            return;
        }

        pb.setVisibility(View.VISIBLE);
        presenter.login(email, password);
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
        runOnUiThread(r);
    }

    @Override
    public void startMainActivity(int code, LoginResponse response) {
        pb.setVisibility(View.INVISIBLE);
        if (code == ResponseCode.BAD_REQUEST) {
            toast("아이디 / 비밀번호를 다시 확인해 주세요.");
            return;
        }
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("Token", response.getResult().getToken());
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void connectFail() {
        pb.setVisibility(View.INVISIBLE);
        toast("서버연결에 실패했습니다. 다시 시도해주세요.");
    }
}
