package com.example.mellov2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class CalibrationNextYesCompleteFragment extends Fragment {


    public CalibrationNextYesCompleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calibration_next_yes_complete, container, false);

        ImageButton okButton = (ImageButton) view.findViewById(R.id.calibration_button_ok);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                        new CalibrationFragment()).commit();
            }
        });

        return view;
    }

}
