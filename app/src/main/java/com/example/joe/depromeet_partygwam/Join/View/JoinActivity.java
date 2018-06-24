package com.example.joe.depromeet_partygwam.Join.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Join.Presenter.JoinContract;
import com.example.joe.depromeet_partygwam.Join.Presenter.JoinPresenter;
import com.example.joe.depromeet_partygwam.Login.View.LoginActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_activity);
        ButterKnife.bind(this);
        presenter = new JoinPresenter();
        presenter.attachView(this);
    }

    @OnTextChanged(R.id.join_emailEdit)
    public void emailTextChange() {
        Log.i(TAG, "emailTextChange");
        presenter.emailValidation(editEmail.getText().toString());
    }

    @OnClick(R.id.join_joinBtn)
    public void joinButtonClick() {
        startActivity(new Intent(JoinActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void toast(final String msg) {
        Runnable r = () -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        };
        runOnUiThread(r);
    }
}
