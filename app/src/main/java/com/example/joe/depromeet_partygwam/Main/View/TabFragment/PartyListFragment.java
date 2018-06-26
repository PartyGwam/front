package com.example.joe.depromeet_partygwam.Main.View.TabFragment;

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

import com.example.joe.depromeet_partygwam.Main.View.Party.Party;
import com.example.joe.depromeet_partygwam.R;

import java.util.List;

public class PartyListFragment extends Fragment {

    private RecyclerView partyListRecyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.party_list_fragment, container, false);
        partyListRecyclerView = (RecyclerView)rootView.findViewById(R.id.party_list_recycler_view);
        partyListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //메인메뉴 아님 바꾸기!!!!!!!!!!
        inflater.inflate(R.menu.party_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //int curId = item.getItemId();
        switch (item.getItemId()) {
            /*
            case R.id.menu_search:
                //Party 객체
                /*
                Party party = new Party();
                PartyLab.get(getActivity()).addParty(crime);

                return true;

            case R.id.menu_search:

                return true;
                */
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class PartyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Party partyEvent;
        private TextView partyLocationTextView;
        private TextView partyTitleTextView;
        private TextView partyDateTextView;


        public PartyHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
        }
        public void bindParty(Party party){

        }

        @Override
        public void onClick(View view){
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
