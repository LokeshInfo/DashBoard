package com.impetrostock.im.Activities.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.R;
import com.impetrostock.im.utility.Utilview;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Add_Warehouse extends Fragment
{
    @BindView(R.id.Ed_WName)
    EditText EdName;
    @BindView(R.id.Ed_WCode)
    EditText EdCode;
    @BindView(R.id.Ed_WCity)
    EditText EdCity;
    @BindView(R.id.Ed_WAddress)
    EditText EdAddress;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Add_Warehouse add_warehouse;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
        add_warehouse = this;             }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_warehouse , container ,false);

        ButterKnife.bind(this,view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("New Warehouse");
        if (((Main_Activity)getActivity()).screenNav())
        {
            ((Main_Activity)getActivity()).lockDrawer();
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utilview.hidekeyboard(getActivity());
                    getActivity().onBackPressed();
                }
            });}

        else
        {   toolbar.setNavigationIcon(R.drawable.menu1);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utilview.hidekeyboard(getActivity());
                    ((Main_Activity)getActivity()).openDrawer();
                }
            });    }

        return view;
    }
}
