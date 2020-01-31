package com.impetrostock.im.Activities.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.R;
import com.impetrostock.im.utility.Utilview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Add_Attributes extends Fragment
{
    @BindView(R.id.SpCat)
    Spinner SpinnerCategory;

    @BindView(R.id.SpSubCat)
    Spinner SpinnerSubCategory;

    @BindView(R.id.AddMore)
    Button AddMore;

    @BindView(R.id.AddAttribute)
    Button AddAttribute;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Add_Attributes add_attributes;
    private LinearLayout Llayout;

    String category[] = {"cat3","Womens","Mens"};
    String Subcategory[] = {""};

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        add_attributes = this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_attributes,container,false);

        ButterKnife.bind(this,view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);
        Llayout = (LinearLayout) view.findViewById(R.id.Llayout);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        ((Main_Activity)getActivity()).lockDrawer();

        txToolbar.setText("New Attributes");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilview.hidekeyboard(getActivity());
                getActivity().onBackPressed();
            }
        });

        ArrayAdapter<String> adcat = Utilview.setupAdapter(getActivity(),SpinnerCategory,category,"Select Category");
        SpinnerCategory.setAdapter(adcat);

        ArrayAdapter<String> adsubc = Utilview.setupAdapter(getActivity(),SpinnerSubCategory,Subcategory,"Select Subcategory");
        SpinnerSubCategory.setAdapter(adsubc);

        return view;
    }

    @OnClick({R.id.AddMore,R.id.AddAttribute})
    public void CLick(View v)
    {
        switch (v.getId())
        {
            case R.id.AddMore:

                LayoutInflater inflater = (LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.ad_field,null);
                Llayout.addView(rowView,Llayout.getChildCount());
                ImageView dlt = rowView.findViewById(R.id.delete);
                dlt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((LinearLayout) rowView.getParent()).removeView(rowView);
                        //Llayout.removeView((View)v.getParent());
                    }
                });
                break;

            case R.id.AddAttribute:
                        getData();
                break;
        }
    }

    private void getData()
    {
        int childcnt = Llayout.getChildCount();
        for(int y=0 ; y<childcnt ; y++)
        {
            View thisChild = Llayout.getChildAt(y);
            TextView Field = (TextView)thisChild.findViewById(R.id.Ed_Name);
            String FieldName = Field.getText().toString();
            Log.e("Field : "+y," is "+FieldName);
        }
    }
}
