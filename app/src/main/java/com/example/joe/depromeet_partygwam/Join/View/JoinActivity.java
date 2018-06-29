package com.example.joe.depromeet_partygwam.Join.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    @BindView(R.id.join_toolbar)
    Toolbar toolbar;
    @BindView(R.id.join_email_invalid)
    TextView textEmailInvalid;
    @BindView(R.id.join_pw_invalid)
    TextView textPwInvalid;
    @BindView(R.id.join_pwConfirm_invalid)
    TextView textPwConfirmInvalid;
    @BindView(R.id.join_nickname_invalid)
    TextView textNicknameInvalid;
    @BindView(R.id.join_pb)
    ProgressBar pb;
    private JoinPresenter presenter;
    private boolean isExistEmail = false;
    private boolean isExistNickname = false;
    private boolean isEmail = false;
    private boolean isPassword = false;
    private boolean isPasswordConfirm = false;
    private boolean isNickname = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_activity);
        ButterKnife.bind(this);

        initToolbar();

        presenter = new JoinPresenter();
        presenter.attachView(this);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @OnTextChanged(R.id.join_emailEdit)
    public void emailTextChange() {
        Log.i(TAG, "emailTextChange");
        presenter.validateRegularExpEmail(editEmail.getText().toString());
    }

    @OnFocusChange(R.id.join_emailEdit)
    public void emailFocusChange(View v, boolean isFocus) {
        if (!isEmail) {
            isExistEmail = false;
            return;
        }

        String email = editEmail.getText().toString();
        if (!isFocus && !email.equals("")) {
            Log.d(TAG, "emailFocusChange");
            pb.setVisibility(View.VISIBLE);
            presenter.validateExistEmail(email);
        }
    }

    @OnTextChanged(R.id.join_pwEdit)
    public void passwordTextChange() {
        presenter.validateRegularExpPassword(editPw.getText().toString());
    }

    @OnTextChanged(R.id.join_pwConfirmEdit)
    public void passwordConfirmTextChange() {
        presenter.validateRegularExpPasswordConfirm(editPwConfirm.getText().toString());
    }

    @OnTextChanged(R.id.join_nicknameEdit)
    public void nicknameTextChange() {
        presenter.validateRegularExpNickname(editNickName.getText().toString());
    }

    @OnFocusChange(R.id.join_nicknameEdit)
    public void nicknameFocusChange(View v, boolean isFocus) {
        if (isFocus && !editNickName.getText().toString().equals("")) {
            Log.d(TAG, "nicknameFocusChange");
        }
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
    public void isExistEmail(int code) {
        pb.setVisibility(View.INVISIBLE);
        if (code == ResponseCode.BAD_REQUEST) {
            isExistEmail = false;
            textEmailInvalid.setText("이메일이 존재합니다.");
            textEmailInvalid.setVisibility(View.VISIBLE);
            return;
        }

        isExistEmail = true;
        textEmailInvalid.setText("사용가능한 이메일입니다.");
        textEmailInvalid.setVisibility(View.VISIBLE);
    }

    @Override
    public void isExistNickname(int code) {
        if (code == ResponseCode.SUCCESS) {

            return;
        }

        if (code == ResponseCode.BAD_REQUEST) {

        }
    }

    @Override
    public void isEmail(boolean b) {
        isEmail = b;
        Log.d(TAG, "isEmail : " + isEmail);
        if (!isEmail) {
            textEmailInvalid.setText("메일 형식으로 입력해주세요. ");
            textEmailInvalid.setVisibility(View.VISIBLE);
            return;
        }

        textEmailInvalid.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isPassword(boolean b) {
        isPassword = b;
        Log.d(TAG, "isPassword : " + isPassword);
        if (!isPassword) {
            textPwInvalid.setVisibility(View.INVISIBLE);
            return;
        }
        textPwInvalid.setVisibility(View.VISIBLE);
    }

    @Override
    public void isPasswordConfirm(boolean b) {
        isPasswordConfirm = b;
        Log.d(TAG, "isPasswordConfirm : " + isPasswordConfirm);
        if (!isPasswordConfirm) {
            textPwConfirmInvalid.setVisibility(View.INVISIBLE);
            return;
        }
        textPwConfirmInvalid.setVisibility(View.VISIBLE);
    }

    @Override
    public void isNickname(boolean b) {
        isNickname = b;
        Log.d(TAG, "isNickname : " + isNickname);
    }

    @Override
    public void setProgressbar(boolean b) {
        if (b) {
            pb.setVisibility(View.VISIBLE);
            return;
        }
        pb.setVisibility(View.INVISIBLE);
        toast("서버연결 오류입니다. 다시 시도해주세요.");
    }
}
