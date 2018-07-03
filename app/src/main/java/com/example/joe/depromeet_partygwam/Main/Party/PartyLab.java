package com.example.joe.depromeet_partygwam.Main.Party;

import android.content.Context;

import com.example.joe.depromeet_partygwam.Data.Parties.PartyResponse;

import java.util.ArrayList;
import java.util.List;

public class PartyLab {
    private static PartyLab sPartyLab;
    private Context context;
    private List<PartyResponse> parties;

    public static PartyLab get(Context context){
        if(sPartyLab == null){
            sPartyLab = new PartyLab(context);
        }
        return sPartyLab;
    }

    public PartyLab(Context context){
        parties = new ArrayList<>();
    }

    public void addParty(PartyResponse p){
        parties.add(p);
    }

    public void deleteParty(PartyResponse p){

    }
}
