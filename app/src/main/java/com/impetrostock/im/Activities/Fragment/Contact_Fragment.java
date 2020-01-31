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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.R;
import com.impetrostock.im.utility.Utilview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Contact_Fragment extends Fragment
{
    @BindView(R.id.Ed_Number)
    EditText edNumber;
    @BindView(R.id.Ed_Email)
    EditText edEmail;
    @BindView(R.id.Ed_addrss)
    EditText edAddress;
    @BindView(R.id.ButtonUpContact)
    Button UptContact;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Contact_Fragment contact_fragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        contact_fragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact,container,false);

        ButterKnife.bind(this,view);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("Update Contact");
        toolbar.setNavigationIcon(R.drawable.menu1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilview.hidekeyboard(getActivity());
                ((Main_Activity)getActivity()).openDrawer();
            }
        });

        return view;
    }

    @OnClick(R.id.ButtonUpContact)
    public void onClick(View v)
    {
        Toast.makeText(activity,"API Required",Toast.LENGTH_SHORT).show();
    }

}
