package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.joe.depromeet_partygwam.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartyListSortPopup extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_list_sort_popup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.party_list_sort_popup_time_text)
    public void onTimeSortClick() {
        Intent intent = new Intent();
        intent.putExtra("Sort", 0);
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.party_list_sort_popup_regi_text)
    public void onRegiSortClick() {
        Intent intent = new Intent();
        intent.putExtra("Sort", 1);
        setResult(RESULT_OK, intent);
        finish();
    }
}
