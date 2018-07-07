package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.PartiesAdapter;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesPresenter;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class PartyListFragment extends Fragment
        implements PartiesContract.View {
    private static final String TAG = PartyListFragment.class.getSimpleName();

    @BindView(R.id.party_list_main_sort_text)
    TextView textSort;
    @BindView(R.id.party_list_main_pb)
    ProgressBar pb;
    @BindView(R.id.party_list_main_list)
    RecyclerView recyclerView;
    private PartiesAdapter adapter;
    private PartiesPresenter presenter;
    private int sort = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_party_list_main, container, false);
        ButterKnife.bind(this, rootView);
        adapter = new PartiesAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //spinner.setSelection(0, false);
        presenter = new PartiesPresenter();
        presenter.attchView(this);
        presenter.setAdapterModel(adapter);
        presenter.setAdapterView(adapter);
    }

    @OnClick(R.id.party_list_main_sort_select)
    public void onSelectClick() {
        startActivityForResult(new Intent(getActivity(), PartyListSortPopup.class), 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                sort = data.getIntExtra("Sort", 0);
                String sortStr = sort == 0 ? "시간순" : "등록순";
                textSort.setText(sortStr);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        refreshList(sort);
    }

    private void refreshList(int position) {
        pb.setVisibility(View.VISIBLE);
        presenter.getParties(position);
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
    public void onConnectFail() {
        pb.setVisibility(View.INVISIBLE);
        toast("서버 연결에 실패했습니다. 다시 시도해주세요.");
    }

    @Override
    public void onSuccessGetList() {
        pb.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");

        //spinner.setOnItemClickListener(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
        Log.d(TAG, "onDetach");
    }
}
