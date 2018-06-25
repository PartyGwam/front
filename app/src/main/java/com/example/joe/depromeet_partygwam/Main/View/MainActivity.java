package com.example.joe.depromeet_partygwam.Main.View;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Login.View.TabFragment.JoinedParty;
import com.example.joe.depromeet_partygwam.Login.View.TabFragment.MyParty;
import com.example.joe.depromeet_partygwam.Login.View.TabFragment.PartyEventMessage;
import com.example.joe.depromeet_partygwam.Login.View.TabFragment.PartyList;
import com.example.joe.depromeet_partygwam.Login.View.TabFragment.SettingProfile;
import com.example.joe.depromeet_partygwam.R;

public class MainActivity extends AppCompatActivity {
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;
    Fragment fragment5;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new PartyList();
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

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //메뉴 아이템 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //menu item 이 클릭됬을 때 호출되는 메소드
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
        }
        return super.onOptionsItemSelected(item);
    }
}
