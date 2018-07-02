package com.example.joe.depromeet_partygwam.Main.View;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.Main.TabFragment.JoinedParty;
import com.example.joe.depromeet_partygwam.Main.TabFragment.MyParty;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyEventMessage;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.View.PartyListFragment;
import com.example.joe.depromeet_partygwam.Main.TabFragment.SettingProfile;
import com.example.joe.depromeet_partygwam.R;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD:app/src/main/java/com/example/joe/depromeet_partygwam/Main/View/MainActivity.java
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;
    private Fragment fragment5;
=======
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;
    Fragment fragment5;

    Toolbar toolbar;
>>>>>>> origin/develop:app/src/main/java/com/example/joe/depromeet_partygwam/Main/View/PartyListActivity.java

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

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

        View tabView1 = getLayoutInflater().inflate(R.layout.tab_layout, null);
        View tabView2 = getLayoutInflater().inflate(R.layout.tab_layout, null);
        View tabView3 = getLayoutInflater().inflate(R.layout.tab_layout, null);
        View tabView4 = getLayoutInflater().inflate(R.layout.tab_layout, null);
        View tabView5 = getLayoutInflater().inflate(R.layout.tab_layout, null);

        TextView tabText1 = (TextView) tabView1.findViewById(R.id.textView);
        TextView tabText2 = (TextView) tabView2.findViewById(R.id.textView);
        TextView tabText3 = (TextView) tabView3.findViewById(R.id.textView);
        TextView tabText4 = (TextView) tabView4.findViewById(R.id.textView);
        TextView tabText5 = (TextView) tabView5.findViewById(R.id.textView);

        tabText1.setText("파티목록");
        tabText2.setText("참여한 파티");
        tabText3.setText("만든 파티");
        tabText4.setText("알림");
        tabText5.setText("설정");

        ImageView tabImage1 = (ImageView) tabView1.findViewById(R.id.imageView);
        ImageView tabImage2 = (ImageView) tabView2.findViewById(R.id.imageView);
        ImageView tabImage3 = (ImageView) tabView3.findViewById(R.id.imageView);
        ImageView tabImage4 = (ImageView) tabView4.findViewById(R.id.imageView);
        ImageView tabImage5 = (ImageView) tabView5.findViewById(R.id.imageView);

        tabImage1.setImageDrawable(getResources().getDrawable(R.drawable.partylist_iconred));
        tabImage2.setImageDrawable(getResources().getDrawable(R.drawable.partici_icon));
        tabImage3.setImageDrawable(getResources().getDrawable(R.drawable.maker_icon));
        tabImage4.setImageDrawable(getResources().getDrawable(R.drawable.notice_icon));
        tabImage5.setImageDrawable(getResources().getDrawable(R.drawable.set_icon));

        //툴바로 적용
        toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        //탭생성
        TabLayout tabs = (TabLayout) findViewById(R.id.main_tabs);
        //탭 버튼 추가하기
        tabs.addTab(tabs.newTab().setCustomView(tabView1));
        tabs.addTab(tabs.newTab().setCustomView(tabView2));
        tabs.addTab(tabs.newTab().setCustomView(tabView3));
        tabs.addTab(tabs.newTab().setCustomView(tabView4));
        tabs.addTab(tabs.newTab().setCustomView(tabView5));

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
