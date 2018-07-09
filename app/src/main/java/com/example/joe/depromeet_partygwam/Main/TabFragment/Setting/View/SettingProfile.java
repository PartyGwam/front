package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.View;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingProfile extends Fragment {
    @BindView(R.id.setting_profile_image)
    ImageView imgProfile;
    @BindView(R.id.setting_profile_nickname)
    EditText editNickname;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_setting_profile, container, false);
        ButterKnife.bind(this, rootView);

        imgProfile.setBackground(new ShapeDrawable(new OvalShape()));
        imgProfile.setClipToOutline(true);
        return rootView;
    }

}
