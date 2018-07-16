package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.PartiesAdapterContract;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.RepliesAdapterContract;
import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailModelCallback;
import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailRetrofitModel;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

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
    public void onSuccessParty(int code, Data data) {
        if (code == ResponseCode.UNAUTHORIZED) {
            view.onAuthorization();
            return;
        }

        if (code == ResponseCode.BAD_REQUEST) {
            view.onBadRequest();
            return;
        }

        if(code == ResponseCode.FORBIDDEN){
            //view.on
            return;
        }
        view.onSuccessUpdateParty(data);
    }

    @Override
    public void onSuccessComment(int code, List<CommentSet> data) {
        if (code == ResponseCode.UNAUTHORIZED) {
            view.onAuthorization();
            return;
        }

        if (code == ResponseCode.BAD_REQUEST) {
            view.onBadRequest();
            return;
        }

        if(code == ResponseCode.FORBIDDEN){
            //view.on
            return;
        }

        view.updateComment(data);
    }

    @Override
    public void onSuccessCommentSend(int code) {
        if (code == ResponseCode.UNAUTHORIZED) {
            view.onAuthorization();
            return;
        }

        if(code == ResponseCode.FORBIDDEN){
            view.onForbidden();
            return;
        }
        view.onSuccessSendComment();
    }

    @Override
    public void getParty(String slug) {
        retrofitModel.getParty(slug);
    }

    @Override
    public void getComments(String slug) {
        retrofitModel.getComments(slug);
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
}

