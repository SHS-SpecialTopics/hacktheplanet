package com.example.rayqu.timertest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class security_upgrade extends Fragment {

    public security_upgrade() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_security_upgrade, container, false);

        view.findViewById(R.id.IButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.IIButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.IButton).setClickable(false);
            }
        });
        view.findViewById(R.id.IIButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.IIIButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.IIButton).setClickable(false);
            }
        });
        view.findViewById(R.id.IIIButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.IVButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.IIIButton).setClickable(false);
            }
        });
        view.findViewById(R.id.IVButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.VButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.IVButton).setClickable(false);
            }
        });
        view.findViewById(R.id.VButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.VButton).setClickable(false);
            }
        });
        view.findViewById(R.id.CryBabyButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.ToddlersTantrumButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.CryBabyButton).setClickable(false);
            }
        });
        view.findViewById(R.id.ToddlersTantrumButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.TeenAngstButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.ToddlersTantrumButton).setClickable(false);
            }
        });
        view.findViewById(R.id.TeenAngstButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.ParentsScornButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.TeenAngstButton).setClickable(false);
            }
        });
        view.findViewById(R.id.ParentsScornButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.ProgrammersRageButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.ParentsScornButton).setClickable(false);
            }
        });
        view.findViewById(R.id.ProgrammersRageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.GodsWrathButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.ProgrammersRageButton).setClickable(false);
            }
        });
        view.findViewById(R.id.MacroVirusButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.FileInfectorButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.MacroVirusButton).setClickable(false);
            }
        });
        view.findViewById(R.id.FileInfectorButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.OverwriteButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.FileInfectorButton).setClickable(false);
            }
        });
        view.findViewById(R.id.OverwriteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.ResidentVirusButton).setVisibility(View.VISIBLE);
                view.findViewById(R.id.OverwriteButton).setClickable(false);
            }
        });

        /*if(savedInstanceState == null) {
            view.findViewById(R.id.IIButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.IIIButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.IVButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.VButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.ToddlersTantrumButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.TeenAngstButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.ParentsScornButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.ProgrammersRageButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.GodsWrathButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.FileInfectorButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.OverwriteButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.ResidentVirusButton).setVisibility(View.INVISIBLE);
        }*/
        return view;
    }

}
