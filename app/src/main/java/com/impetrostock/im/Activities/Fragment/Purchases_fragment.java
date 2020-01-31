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
import com.impetrostock.im.Activities.Adapter.Purchases_Adapter;
import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.Model.Purchases_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Purchases_fragment extends Fragment
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
    Purchases_fragment purchases_fragment;
    Purchases_Adapter adapter;
    List<Purchases_Data> PdataList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
        activity = (Activity)context;
        purchases_fragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_purchases_list,container,false);

        ButterKnife.bind(this,view);
        PdataList.clear();
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("Purchases");
        toolbar.setNavigationIcon(R.drawable.menu1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity)getActivity()).openDrawer();
            }
        });

        itemTouchListener = new OnItemTouchListener() {
            @Override
            public void onViewTap(View view, int position) {
                Purchase_Details_fragment p_detail = new Purchase_Details_fragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,p_detail).addToBackStack("Add_Details").commit();

            }
            @Override
            public void onClickTap(View view, int position) {
                //Intent ain = new Intent(activity, Add_Purchase.class);
                //startActivity(ain);
            }
        };

        PdataList.add(new Purchases_Data("10-05-2019","PO-0337","Lokesh","26000/-"));
        PdataList.add(new Purchases_Data("01-06-2019","PO-0339","Amit","21000/-"));
        PdataList.add(new Purchases_Data("22-08-2019","PO-0367","Shivam","18000/-"));
        adapter = new Purchases_Adapter(activity,PdataList,itemTouchListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @OnClick(R.id.floatingAdd)
    public void onCLick(View v)
    {
        com.impetrostock.im.Activities.Fragment.Add_Purchase adpurc = new com.impetrostock.im.Activities.Fragment.Add_Purchase();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,adpurc).addToBackStack("Add_Purchase").commit();
    }
}
