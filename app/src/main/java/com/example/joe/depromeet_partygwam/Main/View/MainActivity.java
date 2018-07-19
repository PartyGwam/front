package com.example.joe.depromeet_partygwam.Main.View;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.View.HistoryFragment;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.View.JoinedPartyFragment;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.View.MyCreatedPartyFragment;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.View.PartyListFragment;
import com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.View.SettingFragment;
import com.example.joe.depromeet_partygwam.Write.View.PartyWriteActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;
    private Fragment fragment5;

    @BindView(R.id.main_parties_toolbar)
    public Toolbar toolbar;
    @BindView(R.id.main_search_toolbar)
    public Toolbar toolbar2;
    @BindView(R.id.main_view_flipper)
    public ViewFlipper viewFlipper;
    @BindView(R.id.main_toolbar_seach)
    public ImageView imgSearch;
    @BindView(R.id.main_toolbar_write)
    public ImageView imgWrite;
    @BindView(R.id.main_toolbar_text)
    public TextView textTitle;
    @BindView(R.id.main_toolbar_search_confirm)
    public TextView textSearchConfirm;
    @BindView(R.id.main_toolbar_search_edit)
    public EditText editSearch;
    @BindView(R.id.profile_update_back)
    public ImageView imgUpdateBack;
    @BindView(R.id.profile_update_save)
    public TextView textUpdateSave;
    @BindView(R.id.setting_terms_of_use_exit)
    public ImageView exitTermsOfUse;
    @BindView(R.id.main_tab1_img)
    public ImageView imgTab1;
    @BindView(R.id.main_tab2_img)
    public ImageView imgTab2;
    @BindView(R.id.main_tab3_img)
    public ImageView imgTab3;
    @BindView(R.id.main_tab4_img)
    public ImageView imgTab4;
    @BindView(R.id.main_tab5_img)
    public ImageView imgTab5;
    @BindView(R.id.main_tab1_text)
    public TextView textTab1;
    @BindView(R.id.main_tab2_text)
    public TextView textTab2;
    @BindView(R.id.main_tab3_text)
    public TextView textTab3;
    @BindView(R.id.main_tab4_text)
    public TextView textTab4;
    @BindView(R.id.main_tab5_text)
    public TextView textTab5;

    private String token;
    private String uuid;
    private String email;
    private String username;
    private String profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        token = "PG " + intent.getStringExtra("Token");
        uuid = intent.getStringExtra("Uuid");
        email = intent.getStringExtra("Email");
        username = intent.getStringExtra("Username");
        profilePicture = intent.getStringExtra("ProfilePicture");

        SharePreferenceManager.getInstance(this);
        SharePreferenceManager.putString("Token", token);
        SharePreferenceManager.putString("Uuid", uuid);
        SharePreferenceManager.putString("Email", email);
        SharePreferenceManager.putString("Username", username);
        SharePreferenceManager.putString("ProfilePicture", profilePicture);

        initView();
    }

    private void initView() {
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
        fragment2 = new JoinedPartyFragment();
        fragment3 = new MyCreatedPartyFragment();
        fragment4 = new HistoryFragment();
        fragment5 = new SettingFragment();

        //툴바로 적용
        toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.main_tab_parties)
    public void onPartiesClick() {
        imgTab1.setImageDrawable(getDrawable(R.drawable.partylist_iconred));
        textTab1.setTextColor(0xAAe86060);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment1).commit();

        imgTab2.setImageDrawable(getDrawable(R.drawable.bottom02_gray));
        imgTab3.setImageDrawable(getDrawable(R.drawable.bottom03_gray));
        imgTab4.setImageDrawable(getDrawable(R.drawable.bottom04_gray));
        imgTab5.setImageDrawable(getDrawable(R.drawable.bottom05_gray));

        textTab2.setTextColor(0xAA929292);
        textTab3.setTextColor(0xAA929292);
        textTab4.setTextColor(0xAA929292);
        textTab5.setTextColor(0xAA929292);
    }

    @OnClick(R.id.main_tab_joined)
    public void onJoinedClick() {
        imgTab2.setImageDrawable(getDrawable(R.drawable.bottom02_red));
        textTab2.setTextColor(0xAAe86060);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment2).commit();

        imgTab1.setImageDrawable(getDrawable(R.drawable.bottom01_gray));
        imgTab3.setImageDrawable(getDrawable(R.drawable.bottom03_gray));
        imgTab4.setImageDrawable(getDrawable(R.drawable.bottom04_gray));
        imgTab5.setImageDrawable(getDrawable(R.drawable.bottom05_gray));

        textTab1.setTextColor(0xAA929292);
        textTab3.setTextColor(0xAA929292);
        textTab4.setTextColor(0xAA929292);
        textTab5.setTextColor(0xAA929292);
    }

    @OnClick(R.id.main_tab_created)
    public void onCreatedClick() {
        imgTab3.setImageDrawable(getDrawable(R.drawable.bottom03_red));
        textTab3.setTextColor(0xAAe86060);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3).commit();

        imgTab1.setImageDrawable(getDrawable(R.drawable.bottom01_gray));
        imgTab2.setImageDrawable(getDrawable(R.drawable.bottom02_gray));
        imgTab4.setImageDrawable(getDrawable(R.drawable.bottom04_gray));
        imgTab5.setImageDrawable(getDrawable(R.drawable.bottom05_gray));

        textTab1.setTextColor(0xAA929292);
        textTab2.setTextColor(0xAA929292);
        textTab4.setTextColor(0xAA929292);
        textTab5.setTextColor(0xAA929292);
    }

    @OnClick(R.id.main_tab_alarm)
    public void onAlarmClick() {
        imgTab4.setImageDrawable(getDrawable(R.drawable.bottom04_red));
        textTab4.setTextColor(0xAAe86060);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment4).commit();

        imgTab1.setImageDrawable(getDrawable(R.drawable.bottom01_gray));
        imgTab2.setImageDrawable(getDrawable(R.drawable.bottom02_gray));
        imgTab3.setImageDrawable(getDrawable(R.drawable.bottom03_gray));
        imgTab5.setImageDrawable(getDrawable(R.drawable.bottom05_gray));

        textTab1.setTextColor(0xAA929292);
        textTab2.setTextColor(0xAA929292);
        textTab3.setTextColor(0xAA929292);
        textTab5.setTextColor(0xAA929292);
    }

    @OnClick(R.id.main_tab_setting)
    public void onSettingClick() {
        imgTab5.setImageDrawable(getDrawable(R.drawable.bottom05_red));
        textTab5.setTextColor(0xAAe86060);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment5).commit();

        imgTab1.setImageDrawable(getDrawable(R.drawable.bottom01_gray));
        imgTab2.setImageDrawable(getDrawable(R.drawable.bottom02_gray));
        imgTab3.setImageDrawable(getDrawable(R.drawable.bottom03_gray));
        imgTab4.setImageDrawable(getDrawable(R.drawable.bottom04_gray));

        textTab1.setTextColor(0xAA929292);
        textTab2.setTextColor(0xAA929292);
        textTab3.setTextColor(0xAA929292);
        textTab4.setTextColor(0xAA929292);
    }

    @OnClick(R.id.main_toolbar_write)
    public void writeClick() {
        startActivity(new Intent(MainActivity.this, PartyWriteActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharePreferenceManager.remove("Token");
        SharePreferenceManager.remove("Uuid");
        SharePreferenceManager.remove("Email");
        SharePreferenceManager.remove("Username");
        SharePreferenceManager.remove("ProfilePicture");
    }
}
