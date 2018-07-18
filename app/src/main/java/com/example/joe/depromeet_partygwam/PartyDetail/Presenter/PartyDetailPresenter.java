package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.Participant.Participant;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.OnItemClickLIstener;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.RepliesAdapterContract;
import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailModelCallback;
import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailRetrofitModel;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

import java.util.ArrayList;
import java.util.List;

public class PartyDetailPresenter
        implements PartyDetailContract.Presenter, PartyDetailModelCallback.RetrofitCallback, OnItemClickLIstener {

    private PartyDetailContract.View view;
    private PartyDetailRetrofitModel retrofitModel;

    private RepliesAdapterContract.Model adapterModel;
    private RepliesAdapterContract.View adapterView;

    public PartyDetailPresenter() {
        retrofitModel = new PartyDetailRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void onItemClick(CommentSet comment, int position) {
        view.onCommentPopup(comment);
    }

    @Override
    public void onSuccessContentsLoad(int code, Data data) {
        if (code == ResponseCode.SUCCESS) {
            view.onSuccessContentsLoad(data);
            return;
        }

        if (code == ResponseCode.NOT_FOUND) {
            view.onNotFoundContentsLoad();
            return;
        }
    }

    @Override
    public void onSuccessParticipantsLoad(int code, List<Participant> participants) {
        if (code == ResponseCode.NOT_FOUND) {
            view.onNotFoundContentsLoad();
            return;
        }

        if (code == ResponseCode.SUCCESS) {
            view.onSuccessParticipantsLoad(participants);
            return;
        }
    }

    @Override
    public void onSuccessCommentLoad(int code, List<CommentSet> data) {
        if (code == ResponseCode.SUCCESS) {
            view.onSuccessCommentsLoad(data);
            adapterModel.setItems(new ArrayList(data));
            return;
        }

        if (code == ResponseCode.NOT_FOUND) {
            view.onNotFoundCommentsLoad();
            return;
        }
    }

    @Override
    public void onSuccessCommentSend(int code) {
        if (code == ResponseCode.CREATED) {
            view.onSuccessCommentUpdate();
            return;
        }

        if (code == ResponseCode.FORBIDDEN) {
            view.onForbidden("파티 미참석자는 댓글 작성이 불가합니다.");
            return;
        }
    }

    @Override
    public void onSuccessCommentDelete(int code) {
        if (code == ResponseCode.NO_CONTENT) {
            view.onSuccessCommentDelete();
            return;
        }

        if (code == ResponseCode.FORBIDDEN) {
            view.onForbidden("자신의 댓글만 삭제 가능합니다.");
            return;
        }

        if (code == ResponseCode.NOT_FOUND) {
            view.onNotFoundCommentDelete();
            return;
        }
    }

    @Override
    public void onSuccessJoinedParty(int code, String msg) {
        if (code == ResponseCode.CREATED) {
            view.onSuccessParticipantsJoin(msg);
            return;
        }

        if (code == ResponseCode.BAD_REQUEST) {
            view.onBadRequestParticipantsJoin(msg);
            return;
        }
    }

    @Override
    public void onSuccessLeavedParty(int code, String msg) {
        if (code == ResponseCode.NO_CONTENT) {
            view.onSuccessParticipantsCancel(msg);
            return;
        }

        if (code == ResponseCode.BAD_REQUEST) {
            view.onBadrequestParticipantsCancel(msg);
            return;
        }
    }

    @Override
    public void onSuccessCommentUpdate(int code) {
        if (code == ResponseCode.SUCCESS) {
            view.onSuccessCommentModify();
            return;
        }

        if (code == ResponseCode.FORBIDDEN) {
            view.onForbidden("댓글 수정 권한이 없습니다.");
            return;
        }
    }

    @Override
    public void getPartyContents() {
        retrofitModel.getPartyContents();
    }

    @Override
    public void getParticipants() {
        retrofitModel.getParticipants();
    }

    @Override
    public void getComments() {
        retrofitModel.getComments();
    }

    @Override
    public void sendComment(String commentText) {
        retrofitModel.sendComment(commentText);
    }

    @Override
    public void setAdapterView(RepliesAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(this);
    }

    @Override
    public void setAdapterModel(RepliesAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onFailure() {
        view.onConnectFail();
    }

    @Override
    public void attachView(PartyDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void deleteComment(String commentSlug) {
        retrofitModel.deleteComment(commentSlug);
    }

    @Override
    public void updateComment(String commentSlug, String comment) {
        retrofitModel.updateComment(commentSlug, comment);
    }

    @Override
    public void joinParty() {
        retrofitModel.joinParty();
    }

    @Override
    public void leaveParty() {
        retrofitModel.leaveParty();
    }

    @Override
    public void updateOwner() {

    }

    @Override
    public void onAuthorizationError() {
        view.onAuthorization();
    }
}

