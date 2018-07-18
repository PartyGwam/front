package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.Participant.Participant;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.RepliesAdapterContract;

import java.util.List;

public interface PartyDetailContract {
    interface View {
        void toast(String msg);

        void onSuccessContentsLoad(Data data);
        void onNotFoundContentsLoad();

        void onSuccessPartyModify();
        void onBadRequestPartyModify();

        void onSuccessPartyDelete();
        void onBadRequestPartyDelete();

        void onSuccessParticipantsLoad(List<Participant> participants);
        void onNotFoundParticipantsLoad();

        void onSuccessParticipantsJoin(String msg);
        void onBadRequestParticipantsJoin(String msg);

        void onSuccessParticipantsCancel(String msg);
        void onBadrequestParticipantsCancel(String msg);

        void onSuccessOwnerUpdate();
        void onBadRequestOwnerUpdate();

        void onSuccessCommentsLoad(List<CommentSet> comments);
        void onNotFoundCommentsLoad();

        void onSuccessCommentUpdate();
        void onForbiddenCommentUpdate();

        void onSuccessCommentModify();

        void onSuccessCommentDelete();
        void onForbiddenCommentDelete();
        void onNotFoundCommentDelete();

        void onAuthorization();
        void onForbidden(String msg);

        void onConnectFail();

        void updateContents(Data data);
        void updateProfileImages(List<Participant> participants);

        void onCommentPopup(CommentSet commentSet);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();

        void setAdapterView(RepliesAdapterContract.View adapterView);
        void setAdapterModel(RepliesAdapterContract.Model adapterModel);

        void getPartyContents();
        void getParticipants();
        void getComments();

        void sendComment(String commentText);
        void deleteComment(String commentSlug);
        void updateComment();

        void joinParty();
        void leaveParty();

        void updateOwner();
    }
}