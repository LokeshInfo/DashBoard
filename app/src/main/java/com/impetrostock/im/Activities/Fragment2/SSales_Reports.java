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

import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.Activities.Adapter2.Sales_Report_Adapter;
import com.impetrostock.im.Model.Sales_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;
import com.impetrostock.im.utility.Utilview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SSales_Reports extends Fragment
{
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    OnItemTouchListener itemTouchListener;
    SSales_Reports sSales_reports;
    Sales_Report_Adapter adapter;
    List<Sales_Data> PdataList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        sSales_reports = this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_spurchase_report,container,false);

        ButterKnife.bind(this,view);
        PdataList.clear();
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((Main_Activity)getActivity()).unlockDrawer();

        txToolbar.setText("Sale Reports");
        ((Main_Activity)getActivity()).lockDrawer();

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilview.hidekeyboard(getActivity());
                getActivity().onBackPressed();
            }
        });

        PdataList.add(new Sales_Data("11-09-2019","PO-1989","Rahul","Test","1000/-","1200/-","30,000/-","20,000/-","10,000/-"));
        PdataList.add(new Sales_Data("20-09-2019","PO-1990","Lokesh","Test","1000/-","1200/-","30,000/-","20,000/-","10,000/-"));
        PdataList.add(new Sales_Data("29-09-2019","PO-1992","Amit","Test","1000/-","1200/-","30,000/-","20,000/-","10,000/-"));

        adapter = new Sales_Report_Adapter(activity,PdataList,itemTouchListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return view;
    }
}
