package com.example.joe.depromeet_partygwam.PartyDetail.View;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartyEditPopupActivity extends AppCompatActivity {

    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_party_edit_popup);

        Intent intent = getIntent();
        data = intent.getParcelableExtra("item");

        ButterKnife.bind(this);
    }

    @OnClick(R.id.edit_party)
    public void onEditPartyClick() {
        Intent intent = new Intent(getApplicationContext(), EditPartyActivity.class);
        intent.putExtra("item", data);
        startActivity(intent);
        //setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.delete_party)
    public void onDeletePartyClick() {
        //Intent intent = new Intent();
        //intent.putExtra("Sort", 1);
        //setResult(RESULT_OK, intent);
        finish();
    }
}
