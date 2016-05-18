package com.example.rayqu.timertest;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class networking_upgrade extends Fragment {

    public networking_upgrade() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final boolean[] infectedPrograms = {false};
        final boolean[] LAN = {false};
        final boolean[] businessRouters = {false};
        final boolean[] cellTowers = {false};
        final boolean[] satellites = {false};
        final boolean[] MAN = {false};
        final boolean[] mainframes = {false};
        final boolean[] cables = {false};
        final View view = inflater.inflate(R.layout.fragment_networking_upgrade, container, false);

        view.findViewById(R.id.popUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.programsButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.popUpButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                view.findViewById(R.id.popUpButton).setClickable(false);

            }
        });
        view.findViewById(R.id.programsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infectedPrograms[0] = true;
                Button button= (Button) view.findViewById(R.id.programsButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
                if (LAN[0]) {
                    view.findViewById(R.id.manButton).setVisibility(View.VISIBLE);
                }
            }
        });
        view.findViewById(R.id.lanButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LAN[0] =true;
                Button button= (Button) view.findViewById(R.id.lanButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
                if(infectedPrograms[0]){
                    view.findViewById(R.id.manButton).setVisibility(View.VISIBLE);
                }
            }
        });
        view.findViewById(R.id.usbButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.lanButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.usbButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                view.findViewById(R.id.usbButton).setClickable(false);
            }
        });
        view.findViewById(R.id.manButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MAN[0] =true;
                Button button= (Button) view.findViewById(R.id.manButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
                if(satellites[0] && mainframes[0] && cables[0]){
                    view.findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                }
            }
        });
        view.findViewById(R.id.serversTCButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.satelliteButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.serversTCButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                view.findViewById(R.id.serversTCButton).setClickable(false);

            }
        });
        view.findViewById(R.id.satelliteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satellites[0] =true;
                Button button= (Button) view.findViewById(R.id.satelliteButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
                if(MAN[0] && mainframes[0] && cables[0]){
                    view.findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                }
            }
        });
        view.findViewById(R.id.mainframeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainframes[0] = true;
                Button button= (Button) view.findViewById(R.id.mainframeButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
                if (MAN[0] && satellites[0] && cables[0]) {
                    view.findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                }
            }
        });
        view.findViewById(R.id.serversBuisnessButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.mainframeButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.serversBuisnessButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                view.findViewById(R.id.serversBuisnessButton).setClickable(false);
            }
        });

        view.findViewById(R.id.atlanticCablesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cables[0] = true;
                Button button= (Button) view.findViewById(R.id.atlanticCablesButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
                if (MAN[0] && satellites[0] && mainframes[0]) {
                    view.findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                }
            }
        });
        view.findViewById(R.id.homeRoutersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.buisnessRoutersButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.homeRoutersButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                view.findViewById(R.id.homeRoutersButton).setClickable(false);
            }
        });
        view.findViewById(R.id.buisnessRoutersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessRouters[0] = true;
                Button button= (Button) view.findViewById(R.id.buisnessRoutersButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
                if (cellTowers[0]) {
                    view.findViewById(R.id.atlanticCablesButton).setVisibility(View.VISIBLE);
                }
            }
        });
        view.findViewById(R.id.cellTowersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellTowers[0] = true;
                Button button= (Button) view.findViewById(R.id.cellTowersButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
                if (businessRouters[0]) {
                    view.findViewById(R.id.atlanticCablesButton).setVisibility(View.VISIBLE);
                }
            }
        });
        view.findViewById(R.id.internetProvidersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.cellTowersButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.internetProvidersButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                view.findViewById(R.id.internetProvidersButton).setClickable(false);
            }
        });
        view.findViewById(R.id.cloudButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button= (Button) view.findViewById(R.id.cloudButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        /*if(savedInstanceState == null) {
            view.findViewById(R.id.programsButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.lanButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.manButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.satelliteButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.cloudButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.mainframeButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.atlanticCablesButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.buisnessRoutersButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.cellTowersButton).setVisibility(View.INVISIBLE);
        }*/
        // Inflate the layout for this fragment
        return view;
    }
}
