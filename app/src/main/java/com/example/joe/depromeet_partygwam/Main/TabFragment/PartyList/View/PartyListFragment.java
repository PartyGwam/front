package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.PartiesAdapter;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter.PartiesPresenter;
import com.example.joe.depromeet_partygwam.Main.View.MainActivity;
import com.example.joe.depromeet_partygwam.PartyDetail.View.PartyDetailActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.view.View.INVISIBLE;

public class PartyListFragment extends Fragment implements PartiesContract.View {
    private static final String TAG = PartyListFragment.class.getSimpleName();

    @BindView(R.id.party_list_main_appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.party_list_main_sort_text)
    TextView textSort;
    @BindView(R.id.party_list_main_pb)
    ProgressBar pb;
    @BindView(R.id.party_list_main_list)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_party_list_none)
    FrameLayout partyListNone;
    @BindView(R.id.party_research_string)
    TextView researchString;
    protected PartiesAdapter adapter;
    protected PartiesPresenter presenter;
    private int sort = 0;
    private String searchStr;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_party_list_main, container, false);
        ButterKnife.bind(this, rootView);
        ((MainActivity) getActivity()).viewFlipper.setDisplayedChild(0);
        ((MainActivity) getActivity()).textTitle.setText("logo");
        ((MainActivity) getActivity()).imgWrite.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).imgSearch.setVisibility(View.VISIBLE);

        partyListNone.setVisibility(INVISIBLE);

        ((MainActivity) getActivity()).textSearchConfirm.setOnClickListener((v) -> {
            searchStr = ((MainActivity) getActivity()).editSearch.getText().toString();
            InputMethodManager keyboard = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.hideSoftInputFromWindow(((MainActivity) getActivity()).editSearch.getWindowToken(), 0);
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
        pb.setVisibility(INVISIBLE);
        toast("unauthorized error");
    }

    @Override
    public void onUnknownError() {
        pb.setVisibility(INVISIBLE);
        toast("unknown error");
    }

    @Override
    public void onNotFound() {
        pb.setVisibility(INVISIBLE);
        partyListNone.setVisibility(View.VISIBLE);
        researchString.setText("'" + searchStr + "'");

        //toast("게시글이 없습니다.");
    }

    @Override
    public void onConnectFail() {
        pb.setVisibility(INVISIBLE);
        toast("서버 연결에 실패했습니다. 다시 시도해주세요.");
    }

    @Override
    public void startDetailActivity(Data item) {
        Intent intent = new Intent(getActivity().getApplicationContext(), PartyDetailActivity.class);
        intent.putExtra("slug", item.getSlug());
        startActivity(intent);
    }

    @Override
    public void onSuccessGetList() {
        pb.setVisibility(INVISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sort = 0;
        Log.d(TAG, "onDetach");
    }
}
