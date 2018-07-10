package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.View;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.R;

import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingProfilePopup extends AppCompatActivity {
    private static final String TAG = SettingProfilePopup.class.getSimpleName();
    private static final int REQUEST_CAMERA = 900;
    private static final int REQUEST_GALLERY = 901;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting_profile_popup);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.profile_update_popup_capture)
    public void onCaptureClick() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @OnClick(R.id.profile_update_popup_gallary)
    public void onGallaryClick() {
        Uri uri = Uri.parse("content://media/external/images/media");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    @OnClick(R.id.profile_update_popup_picture_delete)
    public void onDeleteClick() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA) {
            if (data != null) {
                Log.d(TAG, "REQUEST_CAMERA");
                Runnable r = () -> {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                };
                new Thread(r).start();
            }
        }

        if (requestCode == REQUEST_GALLERY) {
            if (data != null) {
                Log.d(TAG, "REQUEST_GALLERY");
                Runnable r = () -> {
                    try {
                        AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                        BitmapFactory.Options opt = new BitmapFactory.Options();
                        opt.inSampleSize = 4;
                        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor(), null, opt);
                        afd.close();

                        Intent intent = new Intent();
                        intent.putExtra("image", bitmap);
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
                new Thread(r).start();
            }
        }
    }
}
