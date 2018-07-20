package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.View;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Login.View.LoginActivity;
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
    @BindView(R.id.setting_go_profile)
    ImageView imgEnter;
    @BindView(R.id.setting_profile)
    ImageView imgProfile;
    @BindView(R.id.setting_terms_of_use)
    ImageView TermsOfUseBtn;
    @BindView(R.id.setting_question)
    ImageView sendQuestion;

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
            Glide.with(this)
                    .load(SharePreferenceManager.getString("ProfilePicture"))
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imgProfile);
        }
        return rootView;
    }

    @OnClick(R.id.setting_go_profile)
    public void onSettingUserClick() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingProfileFragment()).commit();
    }

    @OnClick(R.id.setting_terms_of_use)
    public void onSettingTermsOfUseClick(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingTermsOfUseFragment()).commit();
    }
    @OnClick(R.id.setting_question)
    public void onSendQuestionClick(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingQuestionFragment()).commit();
    }

    @OnClick(R.id.setting_logout)
    public void onLogoutClick() {
        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}
