package com.example.joe.depromeet_partygwam.PartyDetail.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartyJoinPopupActivity extends AppCompatActivity {

    @BindView(R.id.join_party_title)
    TextView textTitle;
    @BindView(R.id.join_popup_party_text)
    TextView textContent;
    private boolean isJoined;
    private boolean isOwner;
    private boolean isAlone;
    private int resultCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_party_join_popup);
        ButterKnife.bind(this);

        isJoined = getIntent().getBooleanExtra("isJoined", false);
        isOwner = getIntent().getBooleanExtra("isOwner", false);
        isAlone = getIntent().getBooleanExtra("isAlone", false);

        if (!isOwner && !isJoined) {
            resultCode = 301;
            return;
        }

        if (!isOwner && isJoined) {
            textTitle.setText("파티 참여 취소");
            textContent.setText("파티 참여를 취소하시겠습니까?");
            resultCode = 302;
        }

        if (isOwner && !isAlone) {
            textTitle.setText("파티 참여 취소");
            textContent.setText("방장을 위임하고 탈퇴할 수 있습니다.\n위임하시겠습니까?");
            resultCode = 303;
        }

        if (isOwner && isAlone) {
            textTitle.setText("파티 참여 취소");
            textContent.setText("참여 취소시 게시글이 삭제됩니다.\n계속하시겠습니까?");
            resultCode = 304;
        }
    }

    @OnClick(R.id.yes_button)
    public void onJoinBtnClick() {
        Intent intent = new Intent();
        setResult(resultCode, intent);
        finish();
    }

    @OnClick(R.id.no_button)
    public void noBtnClick() {
        finish();
    }
}
