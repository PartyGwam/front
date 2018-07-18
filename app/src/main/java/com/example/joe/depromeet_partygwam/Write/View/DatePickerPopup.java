package com.example.joe.depromeet_partygwam.Write.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatePickerPopup extends AppCompatActivity {
    private static final String TAG = DatePickerPopup.class.getSimpleName();

    @BindView(R.id.party_write_date_picker)
    DatePicker datePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_activity_date_picker);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.party_write_date_picker_confirm)
    public void onConfirmClick() {
        Log.d(TAG, datePicker.getYear() + "/" + datePicker.getMonth() + "/" + datePicker.getDayOfMonth());
        Intent intent = new Intent();
        intent.putExtra("Year", datePicker.getYear());
        intent.putExtra("Month", datePicker.getMonth());
        intent.putExtra("Day", datePicker.getDayOfMonth());
        setResult(200, intent);
        finish();
    }
}
