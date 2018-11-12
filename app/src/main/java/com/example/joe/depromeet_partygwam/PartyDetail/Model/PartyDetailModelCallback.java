package com.example.joe.depromeet_partygwam.PartyDetail.Model;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.Participant.Participant;

import java.util.List;

public interface PartyDetailModelCallback {
    interface RetrofitCallback {
        void onSuccessContentsLoad(int code, Data data);
        void onSuccessParticipantsLoad(int code, List<Participant> participants);
        void onSuccessCommentLoad(int code, List<CommentSet> data);
        void onSuccessCommentSend(int code);
        void onSuccessCommentDelete(int code);
        void onSuccessCommentUpdate(int code);
        void onSuccessJoinedParty(int code, String msg);
        void onSuccessLeavedParty(int code, String msg);
        void onSuccessDeleteParty(int code);
        void onSuccessOwnerLoad(int code, String owner);
        void onSuccessOwnerUpdate(int code);

        void onAuthorizationError();
        void onFailure();
    }
}
