package com.impetrostock.im.Activities.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.R;
import com.impetrostock.im.utility.Utilview;

import butterknife.ButterKnife;

public class Add_Customer extends Fragment
{
    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Add_Customer add_customer;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
        add_customer = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_customer,container,false);

        ButterKnife.bind(this,view);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);


        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((Main_Activity)getActivity()).lockDrawer();

        txToolbar.setText("New Customer");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilview.hidekeyboard(getActivity());
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}
