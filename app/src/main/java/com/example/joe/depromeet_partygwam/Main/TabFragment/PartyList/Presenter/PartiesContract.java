package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter;

public interface PartyListContract {
    interface View {
        void toast(String msg);
    }

    interface Presenter {
        void getParties(int sort);
        void attchView(View view);
        void detachView();
    }
}
