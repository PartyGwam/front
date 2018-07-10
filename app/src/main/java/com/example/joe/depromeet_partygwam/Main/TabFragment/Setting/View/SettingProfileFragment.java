package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.View;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Presenter.SettingProfileContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Presenter.SettingProfilePresenter;
import com.example.joe.depromeet_partygwam.Main.View.MainActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class SettingProfileFragment extends Fragment implements SettingProfileContract.View {
    @BindView(R.id.setting_profile_image)
    ImageView imgProfile;
    @BindView(R.id.setting_profile_nickname)
    EditText editNickname;
    private SettingProfilePresenter presenter;
    private Bitmap profileTmp;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_setting_profile, container, false);
        ButterKnife.bind(this, rootView);
        presenter = new SettingProfilePresenter(getActivity());

        ((MainActivity) getActivity()).viewFlipper.setDisplayedChild(2);
        imgProfile.setBackground(new ShapeDrawable(new OvalShape()));
        imgProfile.setClipToOutline(true);

        if (!SharePreferenceManager.getString("ProfilePicture").equals("dafault")) {

        }
        editNickname.setText(SharePreferenceManager.getString("Username"));

        ((MainActivity) getActivity()).imgUpdateBack.setOnClickListener((v) -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new SettingFragment()).commit();
        });

        ((MainActivity) getActivity()).textUpdateSave.setOnClickListener((v) -> {
            String userName = editNickname.getText().toString();
            if (profileTmp == null && userName.toString()
                    .equals(SharePreferenceManager.getString("Username"))) {
                toast("변경사항이 없습니다.");
                return;
            }

            if (profileTmp != null) {
                presenter.updateUser(userName, profileTmp);
                return;
            }

            if (profileTmp == null) {
                presenter.updateUser(userName, null);
                return;
            }
        });
        return rootView;
    }



    @OnClick(R.id.setting_profile_image)
    public void onProfileClick() {
        startActivityForResult(new Intent(getActivity(), SettingProfilePopup.class), 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                profileTmp = data.getParcelableExtra("image");
                imgProfile.setImageBitmap(profileTmp);
            }
        }
    }

    @Override
    public void toast(String msg) {
        Runnable r = () -> {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        };
        getActivity().runOnUiThread(r);
    }
}
