package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joe.depromeet_partygwam.Main.View.MainActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends Fragment {
    private static final String TAG = SettingFragment.class.getSimpleName();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.setting_user)
    public void onSettingUserClick() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingProfile()).commit();
    }
}
