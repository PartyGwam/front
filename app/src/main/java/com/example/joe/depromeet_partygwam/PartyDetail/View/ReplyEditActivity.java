package com.example.joe.depromeet_partygwam.PartyDetail.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReplyEditActivity extends AppCompatActivity {
    @BindView(R.id.party_reply_edit_edit)
    EditText editComment;

    private String comment;
    private String contentSlug;
    private String commentSlug;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_reply_edit);
        ButterKnife.bind(this);
        comment = getIntent().getStringExtra("comment");
        commentSlug = getIntent().getStringExtra("comment_slug");
        contentSlug = getIntent().getStringExtra("content_slug");
        editComment.setText(comment);
    }

    @OnClick(R.id.party_reply_edit_back_button)
    public void onBackClick() {
        finish();
    }

    @OnClick(R.id.party_reply_edit_update)
    public void onUpdateClick() {
        if (editComment.getText().toString().equals(comment)) {
            Toast.makeText(this, "변경 내역이 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("comment", editComment.getText().toString());
        intent.putExtra("comment_slug", commentSlug);
        intent.putExtra("content_slug", contentSlug);
        setResult(501, intent);
        finish();
    }

    @OnClick(R.id.party_reply_edit_cancel)
    public void onCancelClick() {
        finish();
    }
}
