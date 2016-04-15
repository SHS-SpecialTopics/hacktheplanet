package com.example.rayqu.timertest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                view.findViewById(R.id.popUpButton).setClickable(false);
            }
        }); ;
        view.findViewById(R.id.programsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infectedPrograms[0] = true;
                if (LAN[0]) {
                    view.findViewById(R.id.manButton).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.programsButton).setClickable(false);
                }
            }
        }); ;
        view.findViewById(R.id.lanButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LAN[0] =true;
                if(infectedPrograms[0]){
                    view.findViewById(R.id.manButton).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.lanButton).setClickable(false);
                }
            }
        }); ;
        view.findViewById(R.id.usbButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.lanButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.usbButton).setClickable(false);
            }
        }); ;
        view.findViewById(R.id.manButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MAN[0] =true;
                if(satellites[0] && mainframes[0] && cables[0]){
                    view.findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.manButton).setClickable(false);
                }
            }
        }); ;
        view.findViewById(R.id.serversTCButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.satelliteButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.serversTCButton).setClickable(false);
            }
        }); ;
        view.findViewById(R.id.satelliteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satellites[0] =true;
                if(MAN[0] && mainframes[0] && cables[0]){
                    view.findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.satelliteButton).setClickable(false);
                }
            }
        }); ;
        view.findViewById(R.id.mainframeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainframes[0] = true;
                if (MAN[0] && satellites[0] && cables[0]) {
                    view.findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.mainframeButton).setClickable(false);
                    view.findViewById(R.id.mainframeButton).getBackground();
                }
            }
        }); ;
        view.findViewById(R.id.serversBuisnessButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.mainframeButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.serversBuisnessButton).setClickable(false);
            }
        }); ;

        view.findViewById(R.id.atlanticCablesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cables[0] = true;
                if (MAN[0] && satellites[0] && mainframes[0]) {
                    view.findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.atlanticCablesButton).setClickable(false);
                }
            }
        }); ;
        view.findViewById(R.id.homeRoutersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.buisnessRoutersButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.homeRoutersButton).setClickable(false);
            }
        }); ;
        view.findViewById(R.id.buisnessRoutersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessRouters[0] = true;
                if (cellTowers[0]) {
                    view.findViewById(R.id.atlanticCablesButton).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.buisnessRoutersButton).setClickable(false);
                }
            }
        }); ;
        view.findViewById(R.id.cellTowersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellTowers[0] = true;
                if (businessRouters[0]) {
                    view.findViewById(R.id.atlanticCablesButton).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.cellTowersButton).setClickable(false);
                }
            }
        }); ;
        view.findViewById(R.id.internetProvidersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.cellTowersButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.internetProvidersButton).setClickable(false);
            }
        }); ;
        if(savedInstanceState == null) {
            view.findViewById(R.id.programsButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.lanButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.manButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.satelliteButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.cloudButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.mainframeButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.atlanticCablesButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.buisnessRoutersButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.cellTowersButton).setVisibility(View.INVISIBLE);
        }
        // Inflate the layout for this fragment
        return view;
    }
}
