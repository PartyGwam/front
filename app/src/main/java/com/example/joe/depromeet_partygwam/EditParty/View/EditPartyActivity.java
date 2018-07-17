package com.example.joe.depromeet_partygwam.EditParty.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.EditParty.Presenter.EditPartyContract;
import com.example.joe.depromeet_partygwam.EditParty.Presenter.EditPartyPresenter;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesPresenter;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPartyActivity extends AppCompatActivity implements EditPartyContract.View{

    private static final String TAG = EditPartyActivity.class.getSimpleName();

    @BindView(R.id.edit_party_title)
    EditText partyTitle;
    @BindView(R.id.edit_party_place)
    EditText partyPlace;
    @BindView(R.id.edit_party_date)
    TextView partyDate;
    @BindView(R.id.edit_party_start_time)
    TextView partyTime;
    @BindView(R.id.edit_party_max_people)
    EditText maxPpl;
    @BindView(R.id.edit_party_content)
    EditText partyContent;
    @BindView(R.id.edit_party_register)
    TextView editPartyBtn;

    private Data data;
    private EditPartyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_party);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        data = intent.getParcelableExtra("item");

        presenter = new EditPartyPresenter();
        presenter.attachView(this);

        onBindView();
    }

    public void onBindView() {
        String startDate = data.getStartTime().split("T")[0];
        String startTime = data.getStartTime().split("T")[1].substring(0, 5);

        Log.d("getData", data.toString());
        partyTitle.setText(data.getTitle());
        partyDate.setText(startDate);
        partyTime.setText(startTime);
        partyPlace.setText(data.getPlace());
        maxPpl.setText(data.getMaxPeople() + "");
        partyContent.setText(data.getDescription());
    }

    @OnClick(R.id.edit_party_back_button)
    public void backButtonClick(){
        finish();
    }

    @OnClick(R.id.edit_party_register)
    public void editPartyRegister(){
        String title = partyTitle.getText().toString();
        String slug = data.getSlug();
        String place = partyPlace.getText().toString();
        String date = partyDate.getText().toString();
        String time = partyTime.getText().toString().split(" ")[0];
        String numOfPeople = maxPpl.getText().toString();
        String contents = partyContent.getText().toString();

        String startTime = date + "T" + time + ":00";

        /*if (title.equals("")) {
            toast("제목이 빈칸입니다.");
            return;
        }

        if (data.getTitle().equals(title) && data.getPlace()) {

            return;
        }*/
        presenter.editParty(title, slug, place, contents,
                startTime, Integer.parseInt(numOfPeople));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void toast(String msg) {
        Runnable r = () -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        };
        runOnUiThread(r);
    }

    @Override
    public void onUnauthorizedError() {
        toast("인증에러 입니다.");
    }

    @Override
    public void onSuccess(String slug) {
        toast("수정 되었습니다.");
        Intent intent = new Intent();
        intent.putExtra("slug", slug);
        setResult(201, intent);
        finish();
    }

    @Override
    public void onBadRequest() {
        toast("");
    }

    @Override
    public void onForbidden() {
        toast("권한이 없습니다.");
    }

    @Override
    public void onConnectFail() {
        toast("서버 연결에 실패했습니다. 다시 시도해주세요.");
    }
}