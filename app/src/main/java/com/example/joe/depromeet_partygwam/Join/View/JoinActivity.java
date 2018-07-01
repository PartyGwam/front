package com.example.joe.depromeet_partygwam.Join.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Join.Presenter.JoinContract;
import com.example.joe.depromeet_partygwam.Join.Presenter.JoinPresenter;
import com.example.joe.depromeet_partygwam.Login.View.LoginActivity;
import com.example.joe.depromeet_partygwam.R;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import butterknife.OnTouch;
import retrofit2.http.POST;

public class JoinActivity extends AppCompatActivity
        implements JoinContract.View, ObserverCallback{
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
    @BindView(R.id.join_check)
    ImageView imgCheck;
    @BindView(R.id.join_joinBtn)
    ImageView btnJoin;
    @BindView(R.id.join_background)
    ConstraintLayout background;
    @BindView(R.id.join_pb)
    ProgressBar pb;
    private JoinPresenter presenter;
    private Boolean isExistEmail = false;
    private Boolean isExistNickname = false;
    private Boolean isEmail = false;
    private Boolean isPassword = false;
    private Boolean isPasswordConfirm = false;
    private Boolean isNickname = false;
    private Boolean isTermOfUse = false;

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

    @OnTouch(R.id.join_background)
    public boolean onBackgroundTouch() {
        setInitViewsFocus();
        return false;
    }

    void setInitViewsFocus() {
        if (editEmail.isFocused()) {
            editEmail.clearFocus();
            return;
        }
        if (editNickName.isFocused()) {
            editNickName.clearFocus();
            return;
        }
    }

    void setViewsEnabled(boolean b) {
        editEmail.setEnabled(b);
        editPw.setEnabled(b);
        editPwConfirm.setEnabled(b);
        editNickName.setEnabled(b);
        imgCheck.setEnabled(b);
        btnJoin.setEnabled(b);
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
            setViewsEnabled(false);
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
        if (!isNickname) {
            isExistNickname = false;
            return;
        }

        String nickname = editNickName.getText().toString();
        if (!isFocus && !nickname.equals("")) {
            Log.d(TAG, "nicknameFocusChange");
            pb.setVisibility(View.VISIBLE);
            setViewsEnabled(false);
            presenter.validateExistNickname(nickname);
        }
    }

    @OnClick(R.id.join_check)
    public void checkButtonClick() {
        setInitViewsFocus();
        if (isTermOfUse) {
            isTermOfUse = false;
            imgCheck.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.checkbox_icon));
        } else {
            isTermOfUse = true;
            imgCheck.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.checkbox_icon02));
        }
    }

    @OnClick(R.id.join_joinBtn)
    public void joinButtonClick() {
        setInitViewsFocus();

        if (!isEmail) {
            toast("이메일을 다시 확인해주세요.");
            return;
        }

        if (!isExistEmail) {
            toast("이메일이 중복됩니다.");
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

        if (!isExistNickname) {
            toast("닉네임이 중복됩니다.");
            return;
        }

        if (!isTermOfUse) {
            toast("이용약관 동의를 해주세요.");
            return;
        }

        pb.setVisibility(View.VISIBLE);
        setViewsEnabled(false);

        User user = new User(editPw.getText().toString(),
                editEmail.getText().toString(),
                editNickName.getText().toString());
        presenter.insertUser(user);
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
        setViewsEnabled(true);
        if (code == ResponseCode.BAD_REQUEST) {
            isExistEmail = false;
            textEmailInvalid.setText("이메일이 존재합니다.");
            textEmailInvalid.setVisibility(View.VISIBLE);
            return;
        }

        isExistEmail = true;
        //textEmailInvalid.setText("사용가능한 이메일입니다.");
        textEmailInvalid.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isExistNickname(int code) {
        pb.setVisibility(View.INVISIBLE);
        setViewsEnabled(true);
        if (code == ResponseCode.BAD_REQUEST) {
            isExistNickname = false;
            textNicknameInvalid.setText("닉네임이 존재합니다.");
            textNicknameInvalid.setVisibility(View.VISIBLE);
            return;
        }

        isExistNickname = true;
        //textNicknameInvalid.setText("사용가능한 닉네임입니다.");
        textNicknameInvalid.setVisibility(View.INVISIBLE);
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
            textPwInvalid.setText("영문, 숫자, 특수문자를 조합하여 6~16자로 설정해주세요.");
            textPwInvalid.setVisibility(View.VISIBLE);
            return;
        }
        //textPwInvalid.setText("사용가능한 패스워드입니다.");
        textPwInvalid.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isPasswordConfirm(boolean b) {
        isPasswordConfirm = b;
        Log.d(TAG, "isPasswordConfirm : " + isPasswordConfirm);
        if (!isPasswordConfirm) {
            textPwConfirmInvalid.setText("비밀번호가 일치하지 않습니다.");
            textPwConfirmInvalid.setVisibility(View.VISIBLE);
            return;
        }
        //textPwConfirmInvalid.setText("비밀번호가 일치합니다.");
        textPwConfirmInvalid.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isNickname(boolean b) {
        isNickname = b;
        Log.d(TAG, "isNickname : " + isNickname);
        if (!isNickname) {
            textNicknameInvalid.setVisibility(View.VISIBLE);
            return;
        }
        textNicknameInvalid.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startLoginActivity(int code) {
        pb.setVisibility(View.INVISIBLE);
        if (code == ResponseCode.BAD_REQUEST) {
            toast("이메일 또는 닉네임 중복을 다시 확인해 주세요.");
            return;
        }

        if (code == ResponseCode.SUCCESS) {
            startActivity(new Intent(JoinActivity.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void setProgressbar(boolean b) {
        if (b) {
            pb.setVisibility(View.VISIBLE);
            return;
        }
        pb.setVisibility(View.INVISIBLE);
        setViewsEnabled(true);
        toast("서버연결 오류입니다. 다시 시도해주세요.");
    }

    @Override
    public void update() {

    }
}
