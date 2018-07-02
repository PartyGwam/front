package com.example.joe.depromeet_partygwam.Main.Party;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.joe.depromeet_partygwam.R;

import java.util.UUID;

public class PartyActivity extends AppCompatActivity {
    Fragment fragment;



    public static Intent newIntent(Context packageContext, UUID partyId){
        Intent intent = new Intent(packageContext, PartyActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.party_activity);

        fragment = new PartyFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment).commit();

    }
}
