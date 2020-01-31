package com.impetrostock.im.Activities.Fragment2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.impetrostock.im.R;
import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.utility.Utilview;

import butterknife.ButterKnife;

public class Product_Details_fragment extends Fragment
{
    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Product_Details_fragment product_details_fragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
        activity = (Activity)context;
        product_details_fragment = this;  }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.product_details_fragment,container,false);

        ButterKnife.bind(this,view);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((Main_Activity)getActivity()).lockDrawer();

        txToolbar.setText("Product Details");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilview.hidekeyboard(getActivity());
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_options_menu,menu);

        menu.removeItem(R.id.subcat);
        menu.add(R.id.group1,R.id.subcat,0,"Edit");

        menu.removeItem(R.id.editcat);
        menu.add(R.id.group1,R.id.editcat,1,"Damage Quantity");

        menu.removeItem(R.id.dltcat);
        menu.add(R.id.group1,R.id.dltcat,2,"Delete");

        menu.add(R.id.group1,R.id.extra,3,"View Barcode");
    }
}
