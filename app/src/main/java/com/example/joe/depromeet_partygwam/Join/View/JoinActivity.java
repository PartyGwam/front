package com.example.joe.depromeet_partygwam.Join.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Join.Presenter.JoinContract;
import com.example.joe.depromeet_partygwam.Join.Presenter.JoinPresenter;
import com.example.joe.depromeet_partygwam.Login.View.LoginActivity;
import com.example.joe.depromeet_partygwam.R;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitMessage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class JoinActivity extends AppCompatActivity implements JoinContract.View{
    private static final String TAG = JoinActivity.class.getSimpleName();

    @BindView(R.id.join_emailEdit)
    EditText editEmail;
    @BindView(R.id.join_pwEdit)
    EditText editPw;
    @BindView(R.id.join_pwConfirmEdit)
    EditText editPwConfirm;
    @BindView(R.id.join_nicknameEdit)
    EditText editNickName;
    private JoinPresenter presenter;
    private boolean isEmail;
    private boolean isPassword;
    private boolean isPasswordConfirm;
    private boolean isNickname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_activity);
        ButterKnife.bind(this);
        presenter = new JoinPresenter();
        presenter.attachView(this);
        //presenter.getMembers();
    }

    @OnTextChanged(R.id.join_emailEdit)
    public void emailTextChange() {
        Log.i(TAG, "emailTextChange");
        presenter.emailValidation(editEmail.getText().toString());
    }

    @OnFocusChange(R.id.join_emailEdit)
    public void emailFocusChange(View v, boolean isFocus) {
        if (isFocus && !editEmail.getText().toString().equals("")) {
            Log.d(TAG, "emailFocusChange");

        }
    }

    @OnTextChanged(R.id.join_pwEdit)
    public void passwordTextChange() {
        presenter.passwordValidation(editPw.getText().toString());
    }

    @OnTextChanged(R.id.join_pwConfirmEdit)
    public void passwordConfirmTextChange() {
        presenter.passwordConfirmValidation(editPwConfirm.getText().toString());
    }

    @OnTextChanged(R.id.join_nicknameEdit)
    public void nicknameTextChange() {
        presenter.nicknameValidation(editNickName.getText().toString());
    }

    @OnClick(R.id.join_joinBtn)
    public void joinButtonClick() {
        if (!isEmail) {
            toast("이메일을 다시 확인해주세요.");
            return;
        }

        if (!isPassword) {
            toast("비밀번호를 다시 확인해주세요.");
            return;
        }

        if (!isPasswordConfirm) {
            toast("비밀번호 중복 확인해주세요.");
            return;
        }

        if (!isNickname) {
            toast("닉네임을 다시 확인해주세요.");
            return;
        }

        startActivity(new Intent(JoinActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void toast(final String msg) {
        Runnable r = () -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        };
        runOnUiThread(r);
    }

    @Override
    public void connectStatus(int code) {
        switch (code) {
            case ResponseCode.SUCCESS:
                break;
            case ResponseCode.BAD_REQUEST:
            case ResponseCode.FORBIDDEN:
            case ResponseCode.NOT_FOUND:

                toast(RetrofitMessage.ERROR_MESSAGE);
                break;
        }
    }

    @Override
    public void isEmail(boolean b) {
        isEmail = b;
        Log.d(TAG, "isEmail : " + isEmail);
    }

    @Override
    public void isPassword(boolean b) {
        isPassword = b;
        Log.d(TAG, "isPassword : " + isPassword);
    }

    @Override
    public void isPasswordConfirm(boolean b) {
        isPasswordConfirm = b;
        Log.d(TAG, "isPasswordConfirm : " + isPasswordConfirm);
    }

    @Override
    public void isNickname(boolean b) {
        isNickname = b;
        Log.d(TAG, "isNickname : " + isNickname);
    }
}
