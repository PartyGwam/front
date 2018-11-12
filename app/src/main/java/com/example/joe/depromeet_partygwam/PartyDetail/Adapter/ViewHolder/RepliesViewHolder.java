package com.example.joe.depromeet_partygwam.PartyDetail.Adapter.ViewHolder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.OnItemClickLIstener;
import com.example.joe.depromeet_partygwam.R;

import org.w3c.dom.Comment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepliesViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = RepliesViewHolder.class.getSimpleName();

    //@BindView(R.id.party_detail_reply_image)
    //ImageView replyImage;
    @BindView(R.id.party_detail_reply_info)
    TextView replyInfo;
    @BindView(R.id.party_detail_reply_content)
    TextView replyContent;
    @BindView(R.id.party_detail_reply_edit)
    ImageView replyEdit;
    @BindView(R.id.party_detail_reply_image)
    ImageView replyImg;
    private Context context;
    private String userName;
    private OnItemClickLIstener onItemClickLIstener;

    public RepliesViewHolder(final Context context, ViewGroup parent, OnItemClickLIstener onItemClickLIstener, String userName) {
        super(LayoutInflater.from(context).inflate(R.layout.party_detail_reply_item, parent, false));
        ButterKnife.bind(this, itemView);
        this.context = context;
        this.userName = userName;
        this.onItemClickLIstener = onItemClickLIstener;
    }

    public void onBind(CommentSet commentSet, int position){
        //Object profileImgSrc = commentSet.getAuthor().getProfilePicture();
        String createTime = commentSet.getCreatedAt().split("T")[1].substring(0, 5);
        String info = commentSet.getAuthor().getUsername() + " | " + createTime;
        //replyImage.setI(profileImgSrc);
        //replyImage.setBackground(new ShapeDrawable(new OvalShape()));
        //replyImage.setClipToOutline(true);
        replyInfo.setText(info);
        replyContent.setText(commentSet.getText());
        Log.d(TAG, "nomal position : " + position);
        if (userName.equals(commentSet.getAuthor().getUsername())){
            replyEdit.setVisibility(View.VISIBLE);
        } else {
            replyEdit.setVisibility(View.GONE);
        }

        if (commentSet.getAuthor().getProfilePicture() != null) {
            Glide.with(itemView)
                    .load(commentSet.getAuthor().getProfilePicture())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(replyImg);
        } else {
            replyImg.setImageDrawable(context.getDrawable(R.drawable.member));
        }

        replyEdit.setOnClickListener((v) -> {
            Log.d(TAG, commentSet.getAuthor().getUsername() + "/" + userName);
            onItemClickLIstener.onItemClick(commentSet, position);
        });
    }
}
