package com.impetrostock.im.Activities.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
import com.impetrostock.im.Activities.Adapter.Products_Adapter;
import com.impetrostock.im.Activities.Fragment2.Product_Details_fragment;
import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.Model.Product_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Products_fragment extends Fragment
{
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.floatingAdd)
    FloatingActionButton floatingAdd;

    private String TAG = Products_fragment.class.getSimpleName();

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Products_Adapter adapter;
    OnItemTouchListener itemTouchListener;
    Products_fragment products_fragment;
    List<Product_Data> dataList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
        activity = (Activity)context;
        products_fragment =this;          }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_products,container,false);

        ButterKnife.bind(this,view);
        dataList.clear();
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("Products");
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
                final Dialog dialog = new Dialog(activity);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(R.layout.dialog_products_list);
                dialog.setTitle("Product Details");

                TextView pcod = (TextView) dialog.findViewById(R.id.pcod);
                TextView pnam = (TextView) dialog.findViewById(R.id.pnam);
                TextView pcat = (TextView) dialog.findViewById(R.id.pcat);
                TextView punit = (TextView) dialog.findViewById(R.id.punit);
                TextView pcost = (TextView) dialog.findViewById(R.id.pcost);
                TextView price = (TextView) dialog.findViewById(R.id.pprice);
                TextView pqty = (TextView) dialog.findViewById(R.id.pqty);
                TextView ptx = (TextView) dialog.findViewById(R.id.pname);
                ImageView imgcancel = (ImageView) dialog.findViewById(R.id.imgcancel);
                ImageView img = (ImageView) dialog.findViewById(R.id.imgBar);
                pcod.setText("Product Code : "+position);
                ptx.setText(dataList.get(position).getName());
                pnam.setText("Product Name : "+dataList.get(position).getName());
                pcat.setText("Product Category : "+dataList.get(position).getCategory());
                punit.setText("Product Unit");
                pcost.setText("Product Cost : "+dataList.get(position).getCost());
                price.setText("Product Price : "+dataList.get(position).getPrice());
                pqty.setText("Product Quantity : "+dataList.get(position).getQuantity());
                dialog.show();
                dialog.setCanceledOnTouchOutside(true);

                imgcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }

            @Override
            public void onClickTap(View view, int position) {
                Product_Details_fragment pdfrag = new Product_Details_fragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,pdfrag).addToBackStack("PD_Details").commit();
            }
        };

        dataList.add(new Product_Data("Fastrack","0989","1999","38999",3,"Men"));
        adapter = new Products_Adapter(activity,dataList,itemTouchListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareData();

        return view;
    }

    @OnClick(R.id.floatingAdd)
    public void onClick(View v)
    {
        Add_Product adProd = new Add_Product();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,adProd).addToBackStack("Add_Product").commit();
    }

    private void prepareData()
    {
        Product_Data dta = new Product_Data("Mens Grooming","TS152009","1500","2000",5,"Men");
        dataList.add(dta);

        dta = new Product_Data("Mens Wear","TS152010","1700","2000",2,"Men");
        dataList.add(dta);

        dta = new Product_Data("Mens Watches","TS152011","6000","4000",9,"Men");
        dataList.add(dta);

        dta = new Product_Data("Mens Shirts","TS152012","4000","10000",4,"Men");
        dataList.add(dta);

        dta = new Product_Data("Trending","TS152013","6000","12000",3,"Men");
        dataList.add(dta);

        dta = new Product_Data("T-Shirts","TS152014","8000","19000",8,"Men");
        dataList.add(dta);

        dta = new Product_Data("Shoes","TS152016","1600","18000",1,"Men");
        dataList.add(dta);

        dta = new Product_Data("Gadgets","TS152020","7000","21000",10,"All");
        dataList.add(dta);

        adapter.notifyDataSetChanged();
    }
}
