package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.PartyDetail.View.PartyDetailActivity;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.PartiesAdapter;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesPresenter;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PartyListFragment extends Fragment implements PartiesContract.View {
    private static final String TAG = PartyListFragment.class.getSimpleName();

    @BindView(R.id.party_list_main_spinner)
    Spinner spinner;
    @BindView(R.id.party_list_main_pb)
    ProgressBar pb;
    @BindView(R.id.party_list_main_list)
    RecyclerView recyclerView;
    private PartiesAdapter adapter;
    private PartiesPresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_party_list_main, container, false);
        ButterKnife.bind(this, rootView);
        ((MainActivity) getActivity()).viewFlipper.setDisplayedChild(0);
        ((MainActivity) getActivity()).textTitle.setText("logo");
        ((MainActivity) getActivity()).imgWrite.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).imgSearch.setVisibility(View.VISIBLE);

        ((MainActivity) getActivity()).textSearchConfirm.setOnClickListener((v) -> {
            String searchStr = ((MainActivity) getActivity()).editSearch.getText().toString();
            if (searchStr.equals("")) {
                refreshList(null, sort);
                return;
            }
            refreshList(searchStr, sort);
        });

        ((MainActivity) getActivity()).imgSearch.setOnClickListener((v) -> {
            ((MainActivity) getActivity()).viewFlipper.setDisplayedChild(1);
        });

        adapter = new PartiesAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(this);

        presenter = new PartiesPresenter();
        presenter.attchView(this);
        presenter.setAdapterModel(adapter);
        presenter.setAdapterView(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "p " + position);
        pb.setVisibility(View.VISIBLE);
        presenter.getParties(position);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        refreshList(null, sort);
    }

    protected void refreshList(String search, int position) {
        pb.setVisibility(View.VISIBLE);
        presenter.getParties(search, position);
    }

    @Override
    public void toast(String msg) {
        Runnable r = () -> {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        };
        getActivity().runOnUiThread(r);
    }

    @Override
    public void onUnauthorizedError() {
        pb.setVisibility(View.INVISIBLE);
        toast("unauthorized error");
    }

    @Override
    public void onUnknownError() {
        pb.setVisibility(View.INVISIBLE);
        toast("unknown error");
    }

    @Override
    public void onNotFound() {
        pb.setVisibility(View.INVISIBLE);
        toast("게시글이 없습니다.");
    }

    @Override
    public void onConnectFail() {
        pb.setVisibility(View.INVISIBLE);
        toast("서버 연결에 실패했습니다. 다시 시도해주세요.");
    }

    @Override
    public void onSuccessGetList() {
        pb.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void startDetailActivity(Data item) {
        Intent intent = new Intent(getActivity().getApplicationContext(), PartyDetailActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
        //결과코드 받는 메서드로 바꾸기
    }
}