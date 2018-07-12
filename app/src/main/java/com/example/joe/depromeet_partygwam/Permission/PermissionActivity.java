package com.example.joe.depromeet_partygwam.Permission;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.joe.depromeet_partygwam.R;
import com.example.joe.depromeet_partygwam.Splash.SplashActivity;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class PermissionActivity extends AppCompatActivity implements PermissionListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_activity);

        TedPermission.with(this)
                .setPermissionListener(this)
                .setRationaleMessage("권한이 필요합니다.")
                .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .check();
    }

    @Override
    public void onPermissionGranted() {
        startActivity(new Intent(PermissionActivity.this, SplashActivity.class));
        finish();
    }

    @Override
    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        Toast.makeText(this, "권한이 필요합니다.", Toast.LENGTH_SHORT).show();
    }
}
