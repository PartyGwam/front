package com.example.joe.depromeet_partygwam.PartyDetail.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReplyEditPopupActivity extends AppCompatActivity {
    @BindView(R.id.edit_party_text)
    TextView textEdit;
    @BindView(R.id.join_popup_party_text)
    TextView textDelete;
    private String commentSlug;
    private String contentSlug;
    private String comment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_edit_popup);
        ButterKnife.bind(this);

        textEdit.setText("댓글 수정");
        textDelete.setText("댓글 삭제");

        comment = getIntent().getStringExtra("comment");
        commentSlug = getIntent().getStringExtra("comment_slug");
        contentSlug = getIntent().getStringExtra("content_slug");
    }

    @OnClick(R.id.edit_party)
    public void onEditClick() {
        Intent intent = new Intent();
        intent.putExtra("comment_slug", commentSlug);
        intent.putExtra("content_slug", contentSlug);
        intent.putExtra("comment", comment);
        setResult(401, intent);
        finish();
    }

    @OnClick(R.id.delete_party)
    public void onDeleteClick() {
        Intent intent = new Intent();
        intent.putExtra("comment_slug", commentSlug);
        setResult(402, intent);
        finish();
    }
}
