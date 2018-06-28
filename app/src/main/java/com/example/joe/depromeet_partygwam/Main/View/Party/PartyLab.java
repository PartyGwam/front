package com.example.joe.depromeet_partygwam.Main.View.Party;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class PartyLab {
    private static PartyLab sPartyLab;
    private Context context;
    private List<Party> parties;

    public static PartyLab get(Context context){
        if(sPartyLab == null){
            sPartyLab = new PartyLab(context);
        }
        return sPartyLab;
    }

    public PartyLab(Context context){
        parties = new ArrayList<>();
    }

    public void addParty(Party p){
        parties.add(p);
    }

    public void deleteParty(Party p){

    }
}
