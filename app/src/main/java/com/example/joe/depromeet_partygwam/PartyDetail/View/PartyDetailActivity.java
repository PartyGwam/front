package com.example.joe.depromeet_partygwam.PartyDetail.View;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesPresenter;
import com.example.joe.depromeet_partygwam.PartyDetail.Presenter.PartyDetailContract;
import com.example.joe.depromeet_partygwam.PartyDetail.Presenter.PartyDetailPresenter;
import com.example.joe.depromeet_partygwam.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.party_info)
    TextView partyInfo;
    @BindView(R.id.join_button)
    ImageView joinBtn;
    @BindView(R.id.num_of_reply)
    TextView numOfReply;
    @BindView(R.id.reply_button)
    FrameLayout replyBtn;

    private PartyDetailPresenter presenter;
    private Data data;
    private Date today;
    private SimpleDateFormat date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        data = intent.getParcelableExtra("item");

        today = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd");

        onBindView();

        presenter = new PartyDetailPresenter();
        presenter.attachView(this);
    }

    public void onBindView(){
        String startDate = data.getStartTime().split("T")[0];
        String startTime = data.getStartTime().split("T")[1].substring(0, 5);
        String createTime = data.getCreatedAt().split("T")[1].substring(0, 5);
        String info = data.getPartyOwner().getUsername() + " | "
                + createTime + " | 조회수 ";

        if (data.getTitle().length() > 13) {
            partyTitle.setText(data.getTitle().substring(0, 13) + "...");
        } else {
            partyTitle.setText(data.getTitle());
        }

        if (startDate.equals(date)) {
            partyDate.setText("오늘 " + startTime);
        }else {
            partyDate.setText(startDate.split("-")[1] + "." + startDate.split("-")[2] + "  " + startTime);
        }

        partyInfo.setText(info);
        partyPlace.setText(data.getPlace());
        joinNumber.setText(data.getCurrentPeople() + "");
        maxNumber.setText(data.getMaxPeople() + "");
        partyContent.setText(data.getDescription());
    }

    @Override
    public void toast(String msg) {
        Runnable r = () -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        };
        runOnUiThread(r);
    }

    @OnClick(R.id.back_button)
    public void backBtnClick(){
        updateParty();
    }

    @OnClick(R.id.edit_party)
    public void editBtnClick(){
        Intent intent = new Intent(getApplicationContext(), PartyEditPopupActivity.class);
        intent.putExtra("item", data);
        startActivityForResult(intent, 101);
    }

    @OnClick(R.id.join_button)
    public void joinBtnClick(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.join_party_btn_dialog_alarm);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateParty(){
        //파티에 수정된 게 있으면 서버로 보내준다음
        //PartyListFragment 로 돌아가기
    }
}
