package com.example.rayqu.timertest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class lethality_upgrade extends Fragment {


    public lethality_upgrade() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lethality_upgrade, container, false);

        view.findViewById(R.id.LaptopButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.DesktopButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.LaptopButton).setClickable(false);
            }
        }); ;
        return view;
    }

}
