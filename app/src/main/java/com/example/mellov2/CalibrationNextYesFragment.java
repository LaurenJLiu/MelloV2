package com.example.mellov2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class CalibrationNextYesFragment extends Fragment {

    //=============================================





    //=============================================

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calibration_next_yes, container, false);

        ImageButton doneCalibrationButton = (ImageButton) view.findViewById(R.id.calibration_button_done);

        doneCalibrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                        new CalibrationNextYesCompleteFragment()).commit();

            }
        });
        return view;
    }
}
