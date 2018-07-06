package com.example.joe.depromeet_partygwam.PartyDetail.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesPresenter;
import com.example.joe.depromeet_partygwam.PartyDetail.Presenter.PartyDetailContract;
import com.example.joe.depromeet_partygwam.PartyDetail.Presenter.PartyDetailPresenter;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PartyDetailActivity extends AppCompatActivity
            implements PartyDetailContract.View{

    private static final String TAG = PartyDetailActivity.class.getSimpleName();

    @BindView(R.id.back_button)
    ImageView backBtn;
    @BindView(R.id.party_title)
    TextView partyTitle;
    @BindView(R.id.edit_party)
    ImageView editParty;
    @BindView(R.id.party_date)
    TextView partyDate;
    @BindView(R.id.party_place)
    TextView partyPlace;
    @BindView(R.id.join_number)
    TextView joinNumber;
    @BindView(R.id.max_number)
    TextView maxNumber;
    @BindView(R.id.party_content)
    TextView partyContent;
    @BindView (R.id.party_leader_image)
    ImageView partyLeaderImage;
    @BindView(R.id.party_leader_nickname)
    TextView partyLeaderNick;
    @BindView(R.id.party_create_time)
    TextView partyCreateTime;
    @BindView(R.id.view_count)
    TextView viewCnt;
    @BindView(R.id.join_button)
    ImageView joinBtn;
    @BindView(R.id.num_of_reply)
    TextView numOfReply;
    @BindView(R.id.reply_button)
    FrameLayout replyBtn;

    private PartyDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);
        ButterKnife.bind(this);

        presenter = new PartyDetailPresenter();
        presenter.attachView(this);
    }

    @Override
    public void toast(String msg) {
        Runnable r = () -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        };
        runOnUiThread(r);
    }

}
