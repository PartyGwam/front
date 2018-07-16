package com.example.joe.depromeet_partygwam.EditParty.Presenter;

import android.util.Log;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.EditParty.Model.EditPartyModelCallback;
import com.example.joe.depromeet_partygwam.EditParty.Model.EditPartyRetrofitModel;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.OnPositionListener;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.PartiesAdapterContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Model.PartiesModelCallback;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Model.PartiesRetrofitModel;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesPresenter;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

import java.util.ArrayList;

public class EditPartyPresenter implements EditPartyContract.Presenter,
        EditPartyModelCallback.RetrofitCallback {
    private static final String TAG = EditPartyPresenter.class.getSimpleName();
    private EditPartyContract.View view;
    private EditPartyRetrofitModel retrofitModel;

    public EditPartyPresenter() {
        retrofitModel = new EditPartyRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void editParty(String title, String slug, String place, String description, String startTime, int maxPeople) {
        retrofitModel.editParty(title, slug, place, description, startTime, maxPeople);
    }


    @Override
    public void attachView(EditPartyContract.View view) {
        this.view = view;
    }

    @Override
    public void onSuccess(int code) {

    }

    @Override
    public void onFailure() {
        view.onConnectFail();
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
