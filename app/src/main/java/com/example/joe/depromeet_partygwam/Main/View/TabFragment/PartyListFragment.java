package com.example.joe.depromeet_partygwam.Main.View.TabFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.Main.View.Party.Party;
import com.example.joe.depromeet_partygwam.Main.View.Party.PartyActivity;
import com.example.joe.depromeet_partygwam.Main.View.Party.PartyLab;
import com.example.joe.depromeet_partygwam.R;

import java.util.List;

public class PartyListFragment extends Fragment {

    private RecyclerView partyListRecyclerView;
    private PartyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_party_list, container, false);

        partyListRecyclerView = (RecyclerView)rootView.findViewById(R.id.party_list_recycler_view);
        partyListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // destroy all menu and re-call onCreateOptionsMenu
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.party_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_search:
                Toast.makeText(getActivity(), "모임 찾기 메뉴", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_add_party:
                Toast.makeText(getActivity(), "모임 추가 메뉴", Toast.LENGTH_LONG).show();
                Party party = new Party();
                PartyLab.get(getActivity()).addParty(party);
                Intent intent = PartyActivity
                        .newIntent(getActivity(), party.getpId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class PartyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Party party;
        private TextView partyDateTextView;
        private TextView partyStartTimeTextView;
        private TextView partyPlaceTextView;
        private TextView partyTitleTextView;


        private PartyHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            partyDateTextView = (TextView)itemView.findViewById(R.id.party_date);
            partyStartTimeTextView = (TextView) itemView.findViewById(R.id.party_time);
            partyPlaceTextView = (TextView)itemView.findViewById(R.id.party_place);
            partyTitleTextView = (TextView) itemView.findViewById(R.id.party_title);
        }

        public void bindParty(Party partyEvent){
            party = partyEvent;
            partyDateTextView.setText(party.getpDate().toString());
            partyStartTimeTextView.setText(party.getpTime().toString());
            partyPlaceTextView.setText(party.getpPlace());
            partyTitleTextView.setText(party.getpTitle());
        }

        @Override
        public void onClick(View view){
            //클릭됬을 때 액티비티? or Fragment? 실행
        }
    }

    private class PartyAdapter extends RecyclerView.Adapter<PartyHolder>{
        private List<Party> partyEvents;

        public PartyAdapter(List<Party> parties){ partyEvents = parties;}

        @Override
        public PartyHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_party_item, parent, false);
            return new PartyHolder(view);
        }

        @Override
        public void onBindViewHolder(PartyHolder holder, int position){
            Party party = partyEvents.get(position);
            holder.bindParty(party);
        }
        @Override
        public int getItemCount(){return partyEvents.size();}
    }
}
