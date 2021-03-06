package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.joe.depromeet_partygwam.Data.UserResponse.UserResponse;
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
    private static final String TAG = SettingProfileFragment.class.getSimpleName();
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
        presenter.attachView(this);
        ((MainActivity) getActivity()).viewFlipper.setDisplayedChild(2);

        Log.d(TAG, SharePreferenceManager.getString("ProfilePicture"));
        if (!SharePreferenceManager.getString("ProfilePicture").equals("default")) {
            Glide.with(this)
                    .load(SharePreferenceManager.getString("ProfilePicture"))
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imgProfile);
        }

        editNickname.setText(SharePreferenceManager.getString("Username"));

        ((MainActivity) getActivity()).imgUpdateBack.setOnClickListener((v) -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new SettingFragment()).commit();
        });

        ((MainActivity) getActivity()).textUpdateSave.setOnClickListener((v) -> {
            String userName = editNickname.getText().toString();
            if (!(userName.length() >= 2 && userName.length() <= 8)) {
                toast("닉네임을 2~8자 이내로 입력 해 주세요.");
                return;
            }

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
                Glide.with(this)
                        .load(new BitmapDrawable(getResources(), profileTmp))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(imgProfile);
                imgProfile.setImageBitmap(profileTmp);
                return;
            }
            if(resultCode == 999){
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

    @Override
    public void onSuccess(UserResponse data) {
        toast("수정 완료 되었습니다.");
        SharePreferenceManager
                .putString("ProfilePicture", data.getResult().getProfile_picture());
        SharePreferenceManager.putString("Username", data.getResult().getUsername());
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingFragment()).commit();
    }

    @Override
    public void onUnauthorizedError() {
        toast("인증에러.");
    }

    @Override
    public void onForbiddenError() {
        toast("수정 권한이 없습니다.");
    }

    @Override
    public void onSupportError() {
        toast("프로필의 닉네임은 이미 존재합니다.");
    }

    @Override
    public void onConnectFail() {
        toast("서버 연결에 실패했습니다. 다시 시도해주세요.");
    }

    @Override
    public void onUsernameOverlap() {
        toast("프로필의 닉네임은 이미 존재합니다.");
    }
}
