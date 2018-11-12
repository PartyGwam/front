package com.example.joe.depromeet_partygwam.Join.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.UserResponse.User;
import com.example.joe.depromeet_partygwam.Join.Presenter.JoinContract;
import com.example.joe.depromeet_partygwam.Join.Presenter.JoinPresenter;
import com.example.joe.depromeet_partygwam.Login.View.LoginActivity;
import com.example.joe.depromeet_partygwam.R;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import butterknife.OnTouch;

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

    private ViewObserver viewObserver;
    private AtomicBoolean isExistEmail;
    private AtomicBoolean isExistNickname;
    private AtomicBoolean isEmail;
    private AtomicBoolean isPassword;
    private AtomicBoolean isPasswordConfirm;
    private AtomicBoolean isNickname;
    private AtomicBoolean isTermOfUse;
    private Boolean isComplete = Boolean.FALSE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_activity);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        isExistEmail = new AtomicBoolean(Boolean.FALSE);
        isExistNickname = new AtomicBoolean(Boolean.FALSE);
        isEmail = new AtomicBoolean(Boolean.FALSE);
        isPassword = new AtomicBoolean(Boolean.FALSE);
        isPasswordConfirm = new AtomicBoolean(Boolean.FALSE);
        isNickname = new AtomicBoolean(Boolean.FALSE);
        isTermOfUse = new AtomicBoolean(Boolean.FALSE);

        viewObserver = new ViewObserver(this);
        viewObserver.add(isExistEmail);
        viewObserver.add(isExistNickname);
        viewObserver.add(isEmail);
        viewObserver.add(isPassword);
        viewObserver.add(isPasswordConfirm);
        viewObserver.add(isNickname);
        viewObserver.add(isTermOfUse);

        presenter = new JoinPresenter();
        presenter.attachView(this);
    }

    @OnClick(R.id.join_toolbar_back)
    public void toolbackBackClick() {
        startActivity(new Intent(JoinActivity.this, LoginActivity.class));
        finish();
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
        if (!isEmail.get()) {
            viewObserver.modifyValue(isExistEmail, Boolean.FALSE);
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
        if (!isNickname.get()) {
            viewObserver.modifyValue(isExistNickname, Boolean.FALSE);
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
        if (isTermOfUse.get()) {
            viewObserver.modifyValue(isTermOfUse, Boolean.FALSE);
            imgCheck.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.checkbox_icon));
        } else {
            viewObserver.modifyValue(isTermOfUse, Boolean.TRUE);
            imgCheck.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.checkbox_icon02));
        }
    }

    @OnClick(R.id.join_joinBtn)
    public void joinButtonClick() {
        setInitViewsFocus();

        if (!isEmail.get()) {
            toast("이메일을 다시 확인해주세요.");
            return;
        }

        if (!isExistEmail.get()) {
            toast("이메일이 중복됩니다.");
            return;
        }

        if (!isPassword.get()) {
            toast("비밀번호를 다시 확인해주세요.");
            return;
        }

        if (!isPasswordConfirm.get()) {
            toast("비밀번호 중복 확인해주세요.");
            return;
        }

        if (!isNickname.get()) {
            toast("닉네임을 다시 확인해주세요.");
            return;
        }

        if (!isExistNickname.get()) {
            toast("닉네임이 중복됩니다.");
            return;
        }

        if (!isTermOfUse.get()) {
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
            viewObserver.modifyValue(isExistEmail, Boolean.FALSE);
            textEmailInvalid.setText("이메일이 존재합니다.");
            textEmailInvalid.setVisibility(View.VISIBLE);
            return;
        }

        viewObserver.modifyValue(isExistEmail, Boolean.TRUE);
        //textEmailInvalid.setText("사용가능한 이메일입니다.");
        textEmailInvalid.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isExistNickname(int code) {
        pb.setVisibility(View.INVISIBLE);
        setViewsEnabled(true);
        if (code == ResponseCode.BAD_REQUEST) {
            viewObserver.modifyValue(isExistNickname, Boolean.FALSE);
            textNicknameInvalid.setText("닉네임이 존재합니다.");
            textNicknameInvalid.setVisibility(View.VISIBLE);
            return;
        }

        viewObserver.modifyValue(isExistNickname, Boolean.TRUE);
        //textNicknameInvalid.setText("사용가능한 닉네임입니다.");
        textNicknameInvalid.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isEmail(boolean b) {
        viewObserver.modifyValue(isEmail, b);
        Log.d(TAG, "isEmail : " + isEmail + "/" + b);
        if (!isEmail.get()) {
            textEmailInvalid.setText("메일 형식으로 입력해주세요. ");
            textEmailInvalid.setVisibility(View.VISIBLE);
            return;
        }

        textEmailInvalid.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isPassword(boolean b) {
        viewObserver.modifyValue(isPassword, b);
        Log.d(TAG, "isPassword : " + isPassword);
        if (!isPassword.get()) {
            textPwInvalid.setText("영문, 숫자, 특수문자를 조합하여 6~16자로 설정해주세요.");
            textPwInvalid.setVisibility(View.VISIBLE);
            return;
        }
        //textPwInvalid.setText("사용가능한 패스워드입니다.");
        textPwInvalid.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isPasswordConfirm(boolean b) {
        viewObserver.modifyValue(isPasswordConfirm, b);
        Log.d(TAG, "isPasswordConfirm : " + isPasswordConfirm);
        if (!isPasswordConfirm.get()) {
            textPwConfirmInvalid.setText("비밀번호가 일치하지 않습니다.");
            textPwConfirmInvalid.setVisibility(View.VISIBLE);
            return;
        }
        //textPwConfirmInvalid.setText("비밀번호가 일치합니다.");
        textPwConfirmInvalid.setVisibility(View.INVISIBLE);
    }

    @Override
    public void isNickname(boolean b) {
        viewObserver.modifyValue(isNickname, b);
        Log.d(TAG, "isNickname : " + isNickname);
        if (!isNickname.get()) {
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
    public void update(boolean b) {
        if (b) {
            btnJoin.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.button02));
            isComplete = Boolean.TRUE;
        } else {
            btnJoin.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.button));
            isComplete = Boolean.FALSE;
        }
    }
}
