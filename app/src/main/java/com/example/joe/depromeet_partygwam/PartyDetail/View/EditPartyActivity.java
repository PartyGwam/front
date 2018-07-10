package com.example.joe.depromeet_partygwam.PartyDetail.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class EditPartyActivity extends AppCompatActivity {

    private static final String TAG = EditPartyActivity.class.getSimpleName();

    @BindView(R.id.party_title)
    EditText partyTitle;
    @BindView(R.id.party_place)
    EditText partyPlace;
    @BindView(R.id.party_date)
    TextView partyDate;
    @BindView(R.id.party_start_time)
    TextView partyTime;
    @BindView(R.id.max_people)
    EditText maxPpl;
    @BindView(R.id.party_content)
    EditText partyContent;

    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_party);

        Intent intent = getIntent();
        data = intent.getParcelableExtra("item");

        onBindView();
    }

    public void onBindView() {
        String startDate = data.getStartTime().split("T")[0];
        String startTime = data.getStartTime().split("T")[1].substring(0, 5);

        //partyTitle.setText(data.getTitle());
        partyDate.setText(startDate);
        partyTime.setText(startTime);
        //partyPlace.setText(data.getPlace());
        //maxPpl.setText(data.getMaxPeople() + "");
        //partyContent.setText(data.getDescription());
    }
}
