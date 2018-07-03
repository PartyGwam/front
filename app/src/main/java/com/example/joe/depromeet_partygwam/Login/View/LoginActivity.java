package com.example.joe.depromeet_partygwam.Login.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.LoginResponse;
import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Join.View.JoinActivity;
import com.example.joe.depromeet_partygwam.Join.View.ObserverCallback;
import com.example.joe.depromeet_partygwam.Join.View.ViewObserver;
import com.example.joe.depromeet_partygwam.Login.Presenter.LoginContract;
import com.example.joe.depromeet_partygwam.Login.Presenter.LoginPresenter;
import com.example.joe.depromeet_partygwam.Main.View.MainActivity;
import com.example.joe.depromeet_partygwam.R;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class LoginActivity extends AppCompatActivity
        implements LoginContract.View, ObserverCallback {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.login_email)
    EditText editEmail;
    @BindView(R.id.login_password)
    EditText editPassword;
    @BindView(R.id.login_loginBtn)
    ImageView loginButton;
    @BindView(R.id.login_loginBtn2)
    ImageView loginButton2;
    @BindView(R.id.login_pb)
    ProgressBar pb;

    private LoginPresenter presenter;
    private ViewObserver viewObserver;
    private AtomicBoolean isEmail;
    private AtomicBoolean isPassword;

    private Boolean isComplete = Boolean.FALSE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        isEmail = new AtomicBoolean(Boolean.FALSE);
        isPassword = new AtomicBoolean(Boolean.FALSE);

        viewObserver = new ViewObserver(this);
        viewObserver.add(isEmail);
        viewObserver.add(isPassword);

        presenter = new LoginPresenter();
        presenter.attachView(this);
    }

    @OnTextChanged(R.id.login_email)
    public void emailTextChange(){
        Log.i(TAG, "emailTextChange");
    }

    @OnFocusChange(R.id.login_email)
    public void emailFocusChange(View v, boolean isFocus) {
        String email = editEmail.getText().toString();
        if (email.equals("")) {
            viewObserver.modifyValue(isEmail, Boolean.FALSE);
            return;
        }
        viewObserver.modifyValue(isEmail, Boolean.TRUE);
    }

    @OnTextChanged(R.id.login_password)
    public void passwordTextChange(){
        Log.i(TAG, "passwordChange");

        String password = editPassword.getText().toString();
        String email = editPassword.getText().toString();
        if (password.equals("")) {
            viewObserver.modifyValue(isPassword, Boolean.FALSE);
            return;
        }
        viewObserver.modifyValue(isPassword, Boolean.TRUE);
    }

    @OnFocusChange(R.id.login_password)
    public void passwordFocusChange(View v, boolean isFocus) {
        String password = editPassword.getText().toString();

        if (password.equals("")) {
            viewObserver.modifyValue(isPassword, Boolean.FALSE);
            return;
        }
        viewObserver.modifyValue(isPassword, Boolean.TRUE);
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

    @Override
    public void update(boolean b) {
        if (b) {
            loginButton2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.bigsignin_yellow));
            isComplete = Boolean.TRUE;
        } else {
            loginButton2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.bigsignin_white));
            isComplete = Boolean.FALSE;
        }
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
