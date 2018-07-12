package com.example.joe.depromeet_partygwam.EditParty.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.EditParty.Presenter.EditPartyPresenter;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesPresenter;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPartyActivity extends AppCompatActivity{

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
        //presenter.attachView(this);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
