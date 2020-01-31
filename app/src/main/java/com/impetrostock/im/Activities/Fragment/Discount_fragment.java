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
import com.impetrostock.im.Activities.Adapter.Discounts_Adapter;
import com.impetrostock.im.Model.Discount_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Discount_fragment extends Fragment
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
    Discount_fragment discount_fragment;
    Discounts_Adapter adapter;
    List<Discount_Data> DdataList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        discount_fragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discount,container,false);

        ButterKnife.bind(this,view);
        DdataList.clear();
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("Discounts");
        toolbar.setNavigationIcon(R.drawable.menu1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity)getActivity()).openDrawer();
            }
        });

        adapter = new Discounts_Adapter(activity,DdataList,itemTouchListener);
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
        Add_Discount adtx = new Add_Discount();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,adtx).addToBackStack("Add_Biller").commit();
    }

    private void prdata()
    {
        DdataList.add(new Discount_Data("60%","60","Percentage(%)"));
        adapter.notifyDataSetChanged();
    }

}
