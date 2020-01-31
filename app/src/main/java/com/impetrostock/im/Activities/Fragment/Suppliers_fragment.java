package com.impetrostock.im.Activities.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.Activities.Adapter.Suppliers_Adapter;
import com.impetrostock.im.Model.Suppliers_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Suppliers_fragment extends Fragment
{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.floatingAdd)
    FloatingActionButton floatingAdd;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    OnItemTouchListener itemTouchListener;
    Suppliers_fragment suppliers_fragment;
    Suppliers_Adapter adapter;
    List<Suppliers_Data> SdataList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        suppliers_fragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_suppliers,container,false);


        ButterKnife.bind(this,view);
        SdataList.clear();
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("Suppliers");
        toolbar.setNavigationIcon(R.drawable.menu1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity)getActivity()).openDrawer();
            }
        });

        adapter = new Suppliers_Adapter(activity,SdataList,itemTouchListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prdata();


        return view;
    }

    @OnClick(R.id.floatingAdd)
    public void onClick(View view)
    {
        Add_Supplier adsupplier = new Add_Supplier();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,adsupplier).addToBackStack("Add_Biller").commit();
    }

    private void prdata()
    {
        SdataList.add(new Suppliers_Data("Anil","Impetrosys","9977699665","anil.impetrosys@outlook.com",
                "Indore, Indore, Madhya Pradesh, India","India"));
        adapter.notifyDataSetChanged();
    }

}
