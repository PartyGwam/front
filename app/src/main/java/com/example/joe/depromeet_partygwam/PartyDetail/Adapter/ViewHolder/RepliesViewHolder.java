package com.example.joe.depromeet_partygwam.PartyDetail.Adapter.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.R;

import org.w3c.dom.Comment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepliesViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = RepliesViewHolder.class.getName();

    @BindView(R.id.party_detail_reply_image)
    ImageView replyImage;
    @BindView(R.id.party_detail_reply_info)
    TextView replyInfo;
    @BindView(R.id.party_detail_reply_content)
    TextView replyContent;
    //수정 버튼은????? Holder에서 처리
    //@BindView(R.id.party_detail_reply_edit)
    //ImageView replyEdit;

    public RepliesViewHolder(final Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.party_detail_reply_item, parent, false));
        ButterKnife.bind(this, itemView);
    }

    public void onBind(CommentSet commentSet, int position, int listSize){
        Object profileImgSrc = commentSet.getAuthor().getProfilePicture();
        String createTime = commentSet.getCreatedAt().split("T")[1].substring(0, 5);
        String info = commentSet.getAuthor() + " | " + createTime;

        //replyImage.setImage(profileImgSrc);
        replyInfo.setText(info);
        replyContent.setText(commentSet.getText());

    }

    @OnClick(R.id.party_detail_reply_edit)
    public void editReplyClick(){

    }
}
