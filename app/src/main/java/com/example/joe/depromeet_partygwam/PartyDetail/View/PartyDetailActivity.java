package com.example.joe.depromeet_partygwam.PartyDetail.View;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.RepliesAdapter;
import com.example.joe.depromeet_partygwam.PartyDetail.Presenter.PartyDetailContract;
import com.example.joe.depromeet_partygwam.PartyDetail.Presenter.PartyDetailPresenter;
import com.example.joe.depromeet_partygwam.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartyDetailActivity extends AppCompatActivity
            implements PartyDetailContract.View{

    private static final String TAG = PartyDetailActivity.class.getSimpleName();

    @BindView(R.id.party_detail_back_button)
    ImageView backBtn;
    @BindView(R.id.party_detail_title)
    TextView partyTitle;
    @BindView(R.id.party_detail_edit_button)
    ImageView editParty;
    @BindView(R.id.party_detail_date)
    TextView partyDate;
    @BindView(R.id.party_detail_place)
    TextView partyPlace;
    @BindView(R.id.party_detail_join_people)
    TextView joinNumber;
    @BindView(R.id.party_detail_max_people)
    TextView maxNumber;
    @BindView(R.id.party_detail_content)
    TextView partyContent;
    @BindView (R.id.party_detail_leader_image)
    ImageView partyLeaderImage;
    @BindView(R.id.party_detail_info)
    TextView partyInfo;
    @BindView(R.id.party_detail_join_button)
    ImageView joinBtn;
    @BindView(R.id.party_detail_reply_count)
    TextView numOfReply;
    @BindView(R.id.reply_list_view)
    RecyclerView replyList;
    @BindView(R.id.party_detail_reply_button)
    FrameLayout replyBtn;
    @BindView(R.id.party_detail_reply_bar)
    EditText replyBar;
    @BindView(R.id.party_detail_progress_bar)
    ProgressBar pb;

    private PartyDetailPresenter presenter;
    private RepliesAdapter adapter;
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

        adapter = new RepliesAdapter(this);
        replyList.setLayoutManager(new LinearLayoutManager(this));
        replyList.setAdapter(adapter);

        presenter = new PartyDetailPresenter();
        presenter.attachView(this);
        presenter.setAdapterModel(adapter);
        presenter.setAdapterView(adapter);
        presenter.getComments(data.getSlug());
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

    @OnClick(R.id.party_detail_back_button)
    public void backBtnClick(){
        updateParty();
    }

    @OnClick(R.id.party_detail_edit_button)
    public void editBtnClick(){
        Intent intent = new Intent(PartyDetailActivity.this, PartyEditPopupActivity.class);
        intent.putExtra("item", data);
        startActivity(intent);
        //startActivityForResult(intent, 101);
    }

    @OnClick(R.id.party_detail_join_button)
    public void joinBtnClick(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.join_party_btn_dialog_alarm);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateParty(){
        //파티에 수정된 게 있으면 서버로 보내준다음
        //PartyListFragment 로 돌아가기
        finish();
    }
    @Override
    public void onAuthorization() {
        pb.setVisibility(View.INVISIBLE);
        toast("인증 에러입니다.");
    }

    @Override
    public void onBadRequest() {
        pb.setVisibility(View.INVISIBLE);
        toast("404");
    }

    @Override
    public void onSuccess() {
        pb.setVisibility(View.INVISIBLE);
        toast("작성 되었습니다.");
    }

    @Override
    public void onConnectFail() {
        pb.setVisibility(View.INVISIBLE);
        toast("서버 연결에 실패했습니다. 다시 시도해주세요.");
    }

    @Override
    public void updateComment(List<CommentSet> data) {
        ArrayList data1 = (ArrayList) data;
        adapter.setItems(data1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
