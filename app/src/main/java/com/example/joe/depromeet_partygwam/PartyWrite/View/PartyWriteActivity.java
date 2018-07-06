package com.example.joe.depromeet_partygwam.PartyWrite.View;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Main.Party.PartyFragment;
import com.example.joe.depromeet_partygwam.PartyWrite.Presenter.PartyWriteContract;
import com.example.joe.depromeet_partygwam.PartyWrite.Presenter.PartyWritePresenter;
import com.example.joe.depromeet_partygwam.R;

import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartyWriteActivity extends AppCompatActivity implements PartyWriteContract.View {
    @BindView(R.id.party_write_title_edit)
    EditText editTitle;
    @BindView(R.id.party_write_place_edit)
    EditText editPlace;
    @BindView(R.id.party_write_date_edit)
    EditText editDate;
    @BindView(R.id.party_write_time_edit)
    EditText editTime;
    @BindView(R.id.party_write_num_of_people_edit)
    EditText editNumOfPeople;
    @BindView(R.id.party_write_content_edit)
    EditText editContent;
    @BindView(R.id.party_write_pb)
    ProgressBar pb;
    @BindView(R.id.party_write_toolbar_back)
    ImageView imgBack;
    @BindView(R.id.party_write_toolbar_regist)
    TextView textRegist;
    private PartyWritePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_write_activity);
        ButterKnife.bind(this);
        presenter = new PartyWritePresenter();
        presenter.attachView(this);
    }

    @OnClick(R.id.party_write_toolbar_back)
    public void onBackClick() {
        finish();
    }

    @OnClick(R.id.party_write_toolbar_regist)
    public void onRegistClick() {
        String title = editTitle.getText().toString();
        String place = editPlace.getText().toString();
        String date = editDate.getText().toString();
        String time = editTime.getText().toString();
        String numOfPeople = editNumOfPeople.getText().toString();
        String contents = editContent.getText().toString();

        if (title.equals("")) {

            return;
        }

        if (place.equals("")) {
            return;
        }

        if (date.equals("")) {
            return;
        }

        if (time.equals("")) {
            return;
        }

        if (numOfPeople.equals("")) {
            return;
        }

        if (contents.equals("")) {
            return;
        }

        String startTime = date + "T" + time;
        presenter.insertParty(title, place, contents,
                startTime, Integer.parseInt(numOfPeople));
    }

    @Override
    public void toast(String msg) {
        Runnable r = () -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        };
        runOnUiThread(r);
    }

    @Override
    public void onAuthorization() {
        pb.setVisibility(View.INVISIBLE);
        toast("인증 에러입니다.");
    }

    @Override
    public void onBadRequest() {
        pb.setVisibility(View.INVISIBLE);
        toast("404");
    }

    @Override
    public void onSuccess() {
        pb.setVisibility(View.INVISIBLE);
        toast("작성 되었습니다.");
        finish();
    }

    @Override
    public void onConnectFail() {
        pb.setVisibility(View.INVISIBLE);
        toast("서버 연결에 실패했습니다. 다시 시도해주세요.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
