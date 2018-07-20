package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.Parties.History.HistoryData;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.HistoryAdapter;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Presenter.HistoryContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Presenter.HistoryPresenter;
import com.example.joe.depromeet_partygwam.Main.View.MainActivity;
import com.example.joe.depromeet_partygwam.PartyDetail.View.PartyDetailActivity;
import com.example.joe.depromeet_partygwam.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment implements HistoryContract.View {
    @BindView(R.id.history_list)
    RecyclerView recyclerView;
    @BindView(R.id.history_pb)
    ProgressBar pb;
    private HistoryAdapter adapter;
    private HistoryPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_history_list, container, false);
        ButterKnife.bind(this, rootView);
        ((MainActivity) getActivity()).viewFlipper.setDisplayedChild(0);
        ((MainActivity) getActivity()).textTitle.setText("알림");
        ((MainActivity) getActivity()).imgWrite.setVisibility(View.INVISIBLE);
        ((MainActivity) getActivity()).imgSearch.setVisibility(View.INVISIBLE);

        adapter = new HistoryAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        presenter = new HistoryPresenter();
        presenter.attachView(this);
        presenter.setAdapterModel(adapter);
        presenter.setAdapterView(adapter);
        presenter.getHistory();
        pb.setVisibility(View.VISIBLE);
        return rootView;
    }

    @Override
    public void toast(String msg) {
        Runnable r = () -> {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        };
        getActivity().runOnUiThread(r);
    }

    @Override
    public void onSuccessHistoryLoad(List<HistoryData> items) {
        pb.setVisibility(View.INVISIBLE);
        presenter.setHistroyView(items);
    }

    @Override
    public void onBadRequestHistoryLoad() {
        pb.setVisibility(View.INVISIBLE);
        //내역이 없을 때
    }

    @Override
    public void onForbidden(String msg) {
        pb.setVisibility(View.INVISIBLE);
        toast("권한이 없습니다.");
    }

    @Override
    public void onAuthorization() {
        pb.setVisibility(View.INVISIBLE);
        toast("인증 에러입니다.");
    }

    @Override
    public void onConnectionFail() {
        pb.setVisibility(View.INVISIBLE);
        toast("서버 연결 오류입니다. 다시 시도해주세요.");
    }

    @Override
    public void startDetailActivity(String slug) {
        Intent intent = new Intent(getActivity(), PartyDetailActivity.class);
        intent.putExtra("slug", slug);
        startActivity(intent);
    }
}
