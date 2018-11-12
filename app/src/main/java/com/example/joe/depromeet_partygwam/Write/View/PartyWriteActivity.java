package com.example.joe.depromeet_partygwam.Write.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Write.Presenter.PartyWriteContract;
import com.example.joe.depromeet_partygwam.Write.Presenter.PartyWritePresenter;
import com.example.joe.depromeet_partygwam.R;

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

    @OnClick(R.id.party_write_date_icon)
    public void onDateClick() {
        startActivityForResult(new Intent(PartyWriteActivity.this, DatePickerPopup.class), 200);
    }

    @OnClick(R.id.party_write_time_icon)
    public void onTimeClick() {
        startActivityForResult(new Intent(PartyWriteActivity.this, TimePickerPopup.class), 100);
    }

    @OnClick(R.id.party_write_toolbar_back)
    public void onBackClick() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == 100) {
            int hour = data.getIntExtra("Hour", 0);
            int minute = data.getIntExtra("Minute", 0);
            editTime.setText(hour + ":" + minute + " ~");
            return;
        }

        if (requestCode == 200 && resultCode == 200) {
            int year = data.getIntExtra("Year", 0);
            int month = data.getIntExtra("Month", 0);
            int day = data.getIntExtra("Day", 0);
            editDate.setText(year + "-" + month + "-" + day);
            return;
        }
    }

    @OnClick(R.id.party_write_toolbar_regist)
    public void onRegistClick() {
        String title = editTitle.getText().toString();
        String place = editPlace.getText().toString();
        String date = editDate.getText().toString();
        String time = editTime.getText().toString().split(" ")[0];
        String numOfPeople = editNumOfPeople.getText().toString();
        String contents = editContent.getText().toString();

        if (title.equals("")) {
            toast("제목을 입력해주세요.");
            return;
        }

        if (place.equals("")) {
            toast("장소를 입력해주세요.");
            return;
        }

        if (date.equals("")) {
            toast("날짜를 선택해주세요.");
            return;
        }

        if (time.equals("")) {
            toast("시간을 선택해주세요.");
            return;
        }

        if (numOfPeople.equals("")) {
            toast("인원을 입력해주세요.");
            return;
        }

        int people = Integer.parseInt(numOfPeople);
        if (people <= 1) {
            toast("인원은 2명 이상부터 가능합니다");
            return;
        }

        if (contents.equals("")) {
            toast("내용을 입력해주세요.");
            return;
        }

        String startTime = date + "T" + time + ":00";
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
