package com.example.joe.depromeet_partygwam.PartyDetail.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.Participant.Participant;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.EditParty.View.EditPartyActivity;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.RepliesAdapter;
import com.example.joe.depromeet_partygwam.PartyDetail.Presenter.PartyDetailContract;
import com.example.joe.depromeet_partygwam.PartyDetail.Presenter.PartyDetailPresenter;
import com.example.joe.depromeet_partygwam.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartyDetailActivity extends AppCompatActivity
            implements PartyDetailContract.View{
    private static final String TAG = PartyDetailActivity.class.getSimpleName();
    public static String SLUG;
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
    @BindView (R.id.party_detail_leader_image)
    ImageView partyLeaderImage;
    @BindViews({R.id.party_leader_image_1, R.id.party_member1,
            R.id.party_member2, R.id.party_member3,
            R.id.party_member4, R.id.party_member5})
    ImageView participantsImage[];

    private PartyDetailPresenter presenter;
    private RepliesAdapter adapter;
    private Date today;
    private SimpleDateFormat date;
    private Data content;
    private List<Participant> participants;
    private boolean isJoined;
    private boolean isOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        SLUG = intent.getStringExtra("slug");

        today = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd");

        adapter = new RepliesAdapter(this);
        replyList.setLayoutManager(new LinearLayoutManager(this));
        replyList.setAdapter(adapter);

        presenter = new PartyDetailPresenter();
        presenter.attachView(this);
        presenter.setAdapterModel(adapter);
        presenter.setAdapterView(adapter);
        presenter.getPartyContents();
        pb.setVisibility(View.VISIBLE);

        InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(replyBar.getWindowToken(), 0);

    }

    @Override
    public void updateContents(Data data) {
        this.content = data;
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
        } else {
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
        finish();
    }

    @OnClick(R.id.party_detail_edit_button)
    public void editBtnClick(){
        Intent intent = new Intent(PartyDetailActivity.this, PartyEditPopupActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == 101) {
            Intent intent = new Intent(PartyDetailActivity.this, EditPartyActivity.class);
            intent.putExtra("item", content);
            startActivityForResult(intent, 200);
            return;
        }

        if (requestCode == 100 && resultCode == 102) {
            pb.setVisibility(View.VISIBLE);
            presenter.deleteParty();
            return;
        }

        if (requestCode == 200 && resultCode == 201) {
            pb.setVisibility(View.VISIBLE);
            presenter.getPartyContents();
            return;
        }

        //일반 참가자 파티 참가
        if (requestCode == 300 && resultCode == 301) {
            pb.setVisibility(View.VISIBLE);
            presenter.joinParty();
            return;
        }

        //일반 참가자 파티 탈퇴
        if (requestCode == 300 && resultCode == 302) {
            pb.setVisibility(View.VISIBLE);
            presenter.leaveParty();
            return;
        }

        //방장 파티 탈퇴, 위임
        if (requestCode == 300 && resultCode == 303) {
            pb.setVisibility(View.VISIBLE);
            presenter.updateOwner(participants.get(1).getUsername());
            return;
        }

        //방장 혼자일 때 파티 탈퇴
        if (requestCode == 300 && resultCode == 304) {
            pb.setVisibility(View.VISIBLE);
            presenter.deleteParty();
            return;
        }

        //댓글 수정 이동
        if (requestCode == 400 && resultCode == 401) {
            Intent intent = new Intent(PartyDetailActivity.this, ReplyEditActivity.class);
            intent.putExtra("comment_slug", data.getStringExtra("comment_slug"));
            intent.putExtra("content_slug", data.getStringExtra("content_slug"));
            intent.putExtra("comment", data.getStringExtra("comment"));
            startActivityForResult(intent, 500);
            return;
        }

        //댓글 삭제
        if (requestCode == 400 && resultCode == 402) {
            pb.setVisibility(View.VISIBLE);
            presenter.deleteComment(data.getStringExtra("comment_slug"));
            return;
        }

        //댓글 수정 완료
        if (requestCode == 500 && resultCode == 501) {
            pb.setVisibility(View.VISIBLE);
            presenter.updateComment(
                    data.getStringExtra("comment_slug"), data.getStringExtra("comment"));
            return;
        }
    }

    @OnClick(R.id.party_detail_join_button)
    public void joinBtnClick() {
        Intent intent = new Intent(PartyDetailActivity.this, PartyJoinPopupActivity.class);
        intent.putExtra("isOwner", isOwner);
        intent.putExtra("isJoined", isJoined);
        intent.putExtra("isAlone", participants.size() == 1 ? true : false);
        startActivityForResult(intent, 300);
    }

    @OnClick(R.id.party_detail_reply_button)
    public void replyBtnClick() {
        if(replyBar.getText().toString().equals("")) {
            toast("댓글란이 공백입니다.");
            return;
        }
        presenter.sendComment(replyBar.getText().toString());
        InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(replyBar.getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }


    @Override
    public void onSuccessContentsLoad(Data data) {
        updateContents(data);
        presenter.getParticipants();
    }

    @Override
    public void onNotFoundContentsLoad() {
        pb.setVisibility(View.INVISIBLE);
        toast("게시글을 찾을 수 없습니다.");
    }

    @Override
    public void onSuccessPartyDelete() {
        pb.setVisibility(View.INVISIBLE);
        toast("삭제 되었습니다.");
        finish();
    }

    @Override
    public void onBadRequestPartyDelete() {
        pb.setVisibility(View.INVISIBLE);
        toast("다른 참여자가 있어 삭제가 불가합니다.");
    }

    @Override
    public void onSuccessParticipantsLoad(List<Participant> participants) {
        this.participants = participants;
        isJoined = isJoined(participants);
        updateProfileImages(participants);
        presenter.getOwner();
    }

    private boolean isJoined(List<Participant> participants) {
        for (Participant participant : participants) {
            if (participant.getUsername().equals(SharePreferenceManager.getString("Username"))) {
                joinBtn.setImageDrawable(getDrawable(R.drawable.unsubscribe_button));
                return true;
            }
        }
        joinBtn.setImageDrawable(getDrawable(R.drawable.parti_button));
        return false;
    }

    @Override
    public void updateProfileImages(List<Participant> participants) {
        int i = 0;
        for (Participant participant : participants) {
            if (participant.getProfilePicture() != null) {
                Glide.with(this)
                        .load(participant.getProfilePicture())
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(participantsImage[i]);
            } else {
                participantsImage[i].setImageDrawable(getDrawable(R.drawable.member));
            }
            i ++;
        }

        if (i <= 6)
            participantsImage[i ++].setImageDrawable(getDrawable(R.drawable.member));

        if (participants.get(0).getProfilePicture() != null)
            Glide.with(this)
                .load(participants.get(0).getProfilePicture())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(partyLeaderImage);
    }


    @Override
    public void onNotFoundParticipantsLoad() {
        pb.setVisibility(View.INVISIBLE);
        toast("파티가 존재하지 않습니다.");
    }

    @Override
    public void onSuccessOwnerLoad(String owner) {
        pb.setVisibility(View.INVISIBLE);
        isOwner = isOwner(owner);
        if (isOwner)
            editParty.setVisibility(View.VISIBLE);
        presenter.getComments();
    }

    private boolean isOwner(String owner) {
        return SharePreferenceManager.getString("Username").equals(owner);
    }

    @Override
    public void onBadRequestOwnerLoad() {
        pb.setVisibility(View.INVISIBLE);
        toast("게시글을 찾을 수 없습니다.");
    }

    @Override
    public void onSuccessOwnerUpdate() {
        pb.setVisibility(View.INVISIBLE);
        toast("다음 참가자로 방장이 위임되었습니다.");
        pb.setVisibility(View.VISIBLE);
        presenter.leaveParty();
    }

    @Override
    public void onBadRequestOwnerUpdate() {
        pb.setVisibility(View.INVISIBLE);
        toast("방장을 위임할 수 없습니다.");
    }

    @Override
    public void onSuccessPartyJoin(String msg) {
        pb.setVisibility(View.INVISIBLE);
        toast(msg);
        pb.setVisibility(View.VISIBLE);
        presenter.getPartyContents();
    }

    @Override
    public void onBadRequestPartyJoin(String msg) {
        pb.setVisibility(View.INVISIBLE);
        toast(msg);
    }

    @Override
    public void onSuccessPartyLeave(String msg) {
        pb.setVisibility(View.INVISIBLE);
        toast(msg);
        pb.setVisibility(View.VISIBLE);
        presenter.getPartyContents();
    }

    @Override
    public void onBadrequestPartyLeave(String msg) {
        pb.setVisibility(View.INVISIBLE);
        toast(msg);
    }

    @Override
    public void onSuccessCommentsLoad(List<CommentSet> comments) {
        pb.setVisibility(View.INVISIBLE);
        numOfReply.setText(comments.size() + "");
        replyList.scrollToPosition(comments.size() - 1);
    }

    @Override
    public void onNotFoundCommentsLoad() {
        //댓글 없을 때 뷰 visible
        pb.setVisibility(View.INVISIBLE);
        numOfReply.setText("0");
    }

    @Override
    public void onSuccessCommentUpdate() {
        pb.setVisibility(View.INVISIBLE);
        toast("작성 되었습니다.");
        replyBar.setText("");
        pb.setVisibility(View.VISIBLE);
        presenter.getComments();
    }

    @Override
    public void onSuccessCommentModify() {
        pb.setVisibility(View.INVISIBLE);
        toast("댓글이 수정되었습니다.");
        pb.setVisibility(View.VISIBLE);
        presenter.getComments();
    }

    @Override
    public void onSuccessCommentDelete() {
        pb.setVisibility(View.INVISIBLE);
        toast("댓글이 삭제되었습니다.");
        pb.setVisibility(View.VISIBLE);
        presenter.getComments();
    }

    @Override
    public void onNotFoundCommentDelete() {
        pb.setVisibility(View.INVISIBLE);
        toast("댓글을 찾을 수 없습니다.");
    }

    @Override
    public void onAuthorization() {
        pb.setVisibility(View.INVISIBLE);
        toast("인증값이 없습니다.");
    }

    @Override
    public void onForbidden(String msg) {
        pb.setVisibility(View.INVISIBLE);
        toast(msg);
    }

    @Override
    public void onConnectFail() {
        pb.setVisibility(View.INVISIBLE);
        toast("서버 연결에 실패했습니다. 다시 시도해주세요.");
    }

    @Override
    public void onCommentPopup(CommentSet commentSet) {
        Intent intent = new Intent(PartyDetailActivity.this, ReplyEditPopupActivity.class);
        intent.putExtra("comment_slug", commentSet.getSlug());
        intent.putExtra("content_slug", SLUG);
        intent.putExtra("comment", commentSet.getText());
        startActivityForResult(intent, 400);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //presenter.detachView();
    }
}