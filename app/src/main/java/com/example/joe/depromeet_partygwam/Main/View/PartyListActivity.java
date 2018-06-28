package com.example.joe.depromeet_partygwam.Main.View;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.joe.depromeet_partygwam.Main.View.TabFragment.JoinedParty;
import com.example.joe.depromeet_partygwam.Main.View.TabFragment.MyParty;
import com.example.joe.depromeet_partygwam.Main.View.TabFragment.PartyEventMessage;
import com.example.joe.depromeet_partygwam.Main.View.TabFragment.PartyListFragment;
import com.example.joe.depromeet_partygwam.Main.View.TabFragment.SettingProfile;
import com.example.joe.depromeet_partygwam.R;

public class PartyListActivity extends AppCompatActivity {

    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;
    Fragment fragment5;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_list);

        FragmentManager fm = getSupportFragmentManager();
        Fragment tab1_fragment = fm.findFragmentById(R.id.fragment_container);

        //첫번째 탭 프래그먼트 보이기 이전에 액티비티의 레이아웃 보여주기 방지
        if(tab1_fragment == null){
            tab1_fragment = new PartyListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, tab1_fragment)
                    .commit();
        }

        fragment1 = tab1_fragment;
        fragment2 = new JoinedParty();
        fragment3 = new MyParty();
        fragment4 = new PartyEventMessage();
        fragment5 = new SettingProfile();

        //툴바로 적용
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //탭생성
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        //탭 버튼 추가하기
        tabs.addTab(tabs.newTab().setText("파티목록"));
        tabs.addTab(tabs.newTab().setText("참여한모임"));
        tabs.addTab(tabs.newTab().setText("만든모임"));
        tabs.addTab(tabs.newTab().setText("알림"));
        tabs.addTab(tabs.newTab().setText("설정"));

        //탭 화면 전환
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = fragment1;
                } else if (position == 1) {
                    selected = fragment2;
                } else if (position == 2) {
                    selected = fragment3;
                } else if (position == 3){
                    selected = fragment4;
                } else if (position == 4){
                    selected = fragment5;
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
