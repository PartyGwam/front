package com.example.joe.depromeet_partygwam.PartyDetail.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.EditParty.View.EditPartyActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartyJoinPopupActivity extends AppCompatActivity {

    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_party_join_popup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.yes_button)
    public void onJoinBtnClick() {

    }

    @OnClick(R.id.no_button)
    public void noBtnClick() {
        //Intent intent = new Intent();
        //intent.putExtra("Sort", 1);
        //setResult(RESULT_OK, intent);
        finish();
    }
}
