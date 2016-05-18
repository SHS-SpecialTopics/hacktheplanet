package com.example.rayqu.timertest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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
        final boolean[] mainframes = {false};
        final boolean[] drones = {false};
        final View view = inflater.inflate(R.layout.fragment_lethality_upgrade, container, false);

        view.findViewById(R.id.LaptopButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.DesktopButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.LaptopButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.DesktopButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.StationsButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.DesktopButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.StationsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.MobilePhonesButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.StationsButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.MobilePhonesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.SmartPhonesButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.MobilePhonesButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.SmartPhonesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.TablesButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.SmartPhonesButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.TablesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.UCCButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.TablesButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.GovernmentSatellitesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.RovButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.GovernmentSatellitesButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.WifiScrammbleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.RandomRebootsButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.WifiScrammbleButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.RovButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.WarsuitsButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.RovButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.WarsuitsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.DronesButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.WarsuitsButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });

        view.findViewById(R.id.RandomRebootsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.ServersButton).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.RandomRebootsButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);

            }
        });
        view.findViewById(R.id.ServersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.Mainframe1Button).setVisibility(View.VISIBLE);
                Button button= (Button) view.findViewById(R.id.ServersButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.UCCButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button= (Button) view.findViewById(R.id.UCCButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.LaunchCodesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button= (Button) view.findViewById(R.id.LaunchCodesButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
            }
        });
        view.findViewById(R.id.DronesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drones[0]=true;
                Button button= (Button) view.findViewById(R.id.DronesButton);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
                if(mainframes[0]){
                    view.findViewById(R.id.LaunchCodesButton).setVisibility(View.VISIBLE);
                }
            }
        });
        view.findViewById(R.id.Mainframe1Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainframes[0]=true;
                Button button= (Button) view.findViewById(R.id.Mainframe1Button);
                button.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                button.setClickable(false);
                if(drones[0]){
                    view.findViewById(R.id.LaunchCodesButton).setVisibility(View.VISIBLE);
                }
            }
        });
        /*if(savedInstanceState == null) {
            view.findViewById(R.id.DesktopButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.StationsButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.MobilePhonesButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.SmartPhonesButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.TablesButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.UCCButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.LaunchCodesButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.RovButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.RandomRebootsButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.ServersButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.Mainframe1Button).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.DronesButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.WarsuitsButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.RovButton).setVisibility(View.INVISIBLE);
        }*/
        return view;
    }

}
