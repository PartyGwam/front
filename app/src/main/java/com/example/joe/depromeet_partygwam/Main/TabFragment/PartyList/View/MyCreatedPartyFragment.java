package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joe.depromeet_partygwam.Main.View.MainActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.ButterKnife;

public class MyCreatedPartyFragment extends PartyListFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        super.appBarLayout.setScaleY(0.0f);
        ((MainActivity) getActivity()).textTitle.setText("내가 만든 파티");
        ((MainActivity) getActivity()).imgWrite.setVisibility(View.INVISIBLE);
        ((MainActivity) getActivity()).imgSearch.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSelectClick() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void refreshList(String search, int position) {
        super.pb.setVisibility(View.VISIBLE);
        super.presenter.getCreatedParties();
    }

    @Override
    public void toast(String msg) {
        super.toast(msg);
    }

    @Override
    public void onUnauthorizedError() {
        super.onUnauthorizedError();
    }

    @Override
    public void onUnknownError() {
        super.onUnknownError();
    }

    @Override
    public void onConnectFail() {
        super.onConnectFail();
    }

    @Override
    public void onSuccessGetList() {
        super.onSuccessGetList();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
