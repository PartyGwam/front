package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.Participant.Participant;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.RepliesAdapterContract;
import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailModelCallback;
import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailRetrofitModel;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

import java.util.ArrayList;
import java.util.List;

public class PartyDetailPresenter
        implements PartyDetailContract.Presenter, PartyDetailModelCallback.RetrofitCallback {

    private PartyDetailContract.View view;
    private PartyDetailRetrofitModel retrofitModel;

    private RepliesAdapterContract.Model adapterModel;
    private RepliesAdapterContract.View adapterView;

    public PartyDetailPresenter() {
        retrofitModel = new PartyDetailRetrofitModel();
        retrofitModel.setCallback(this);
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
    public void sendComment(String commentText, String slug) {
        retrofitModel.sendComment(commentText, slug);
    }

    @Override
    public void setAdapterView(RepliesAdapterContract.View adapterView) {
        this.adapterView = adapterView;
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
    public void deleteComment() {

    }

    @Override
    public void updateComment() {

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

