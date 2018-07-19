package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.joe.depromeet_partygwam.Main.View.MainActivity;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;


public class SettingQuestionFragment extends Fragment {

    @BindView(R.id.questionText)
    EditText questionText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_setting_question, container, false);
        ((MainActivity) getActivity()).viewFlipper.setDisplayedChild(4);

        ((MainActivity) getActivity()).exitSendQuestion.setOnClickListener((v) -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new SettingFragment()).commit();
        });

        //문의하기 보내는 코드 짜야함.
        return rootView;
    }
}
