package com.example.joe.depromeet_partygwam.Write.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TimePicker;

import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimePickerPopup extends AppCompatActivity implements TimePicker.OnTimeChangedListener {
    private static final String TAG = TimePickerPopup.class.getSimpleName();

    @BindView(R.id.party_write_time_picker)
    TimePicker timePicker;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_activity_time_picker);
        ButterKnife.bind(this);
        Log.d(TAG, timePicker.getCurrentHour() + "/" + timePicker.getCurrentMinute());
        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();

        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        Log.d(TAG, hourOfDay + "/" + minute);
        this.hour = hourOfDay;
        this.minute = minute;
    }

    @OnClick(R.id.party_write_time_picker_confirm)
    public void onConfirmClick() {
        Intent intent = new Intent();
        intent.putExtra("Hour", hour);
        intent.putExtra("Minute", minute);
        setResult(100, intent);
        finish();
    }
}
