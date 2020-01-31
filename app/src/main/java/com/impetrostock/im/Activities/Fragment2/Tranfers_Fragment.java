package com.impetrostock.im.Activities.Fragment2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.impetrostock.im.Activities.Adapter2.Transfers_Adapter;
import com.impetrostock.im.Model.Transfers_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Tranfers_Fragment extends Fragment
{
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.floatingAdd)
    FloatingActionButton floatingAdd;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Transfers_Adapter adapter;
    OnItemTouchListener itemTouchListener;
    Tranfers_Fragment tranfers_fragment;
    List<Transfers_Data> dataList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        tranfers_fragment = this;         }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_billers,container,false);

        ButterKnife.bind(this,view);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((Main_Activity)getActivity()).unlockDrawer();

        txToolbar.setText("Transfers Product");
        toolbar.setNavigationIcon(R.drawable.menu1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity)getActivity()).openDrawer();
            }
        });

        dataList.add(new Transfers_Data("10-05-2019","PO-0337","Indore","Devas"));
        dataList.add(new Transfers_Data("01-06-2019","PO-0339","Ujjain","Bhopal"));
        dataList.add(new Transfers_Data("22-08-2019","PO-0367","Pune","Nasik"));
        adapter = new Transfers_Adapter(activity,dataList,itemTouchListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @OnClick(R.id.floatingAdd)
    public void onClick(View view)
    {
        Add_Transfer adtrns = new Add_Transfer();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,adtrns).addToBackStack("Add_Transfer").commit();
    }
}
