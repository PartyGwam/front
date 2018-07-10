package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Main.View.MainActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends Fragment {
    private static final String TAG = SettingFragment.class.getSimpleName();
    @BindView(R.id.setting_nickname)
    TextView textNickname;
    @BindView(R.id.setting_email)
    TextView textEmail;
    @BindView(R.id.setting_user)
    ImageView imgUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, rootView);
        ((MainActivity) getActivity()).viewFlipper.setDisplayedChild(0);
        ((MainActivity) getActivity()).textTitle.setText("설정");
        ((MainActivity) getActivity()).imgWrite.setVisibility(View.INVISIBLE);
        ((MainActivity) getActivity()).imgSearch.setVisibility(View.INVISIBLE);

        textNickname.setText(SharePreferenceManager.getString("Username"));
        textEmail.setText(SharePreferenceManager.getString("Email"));
        if (!SharePreferenceManager.getString("ProfilePicture").equals("default")) {
            //사진 지정
        }
        return rootView;
    }

    @OnClick(R.id.setting_user)
    public void onSettingUserClick() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingProfileFragment()).commit();
    }
}
