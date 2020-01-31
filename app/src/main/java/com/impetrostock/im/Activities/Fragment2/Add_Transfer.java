package com.impetrostock.im.Activities.Fragment2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.R;
import com.impetrostock.im.utility.Utilview;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Add_Transfer extends Fragment
{
    @BindView(R.id.Ed_date)
    EditText edDate;

    @BindView(R.id.SpFrom)
    Spinner SpinnerFrom;

    @BindView(R.id.SpTo)
    Spinner SpinnerTo;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Add_Transfer add_transfer;
    DatePickerDialog picker;

    String sfrom[] = {"Madhya Pradesh","Rajasthan","Uttar Pradesh"};

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        add_transfer=this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_transfer,container,false);
        ButterKnife.bind(this,view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((Main_Activity)getActivity()).lockDrawer();

        txToolbar.setText("Transfer Product");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilview.hidekeyboard(getActivity());
                getActivity().onBackPressed();
            }
        });

        getDate();

        ArrayAdapter<String> adFrom = Utilview.setupAdapter(activity,SpinnerFrom,sfrom,"Select Warehouse(From)");
        SpinnerFrom.setAdapter(adFrom);

        ArrayAdapter<String> adTo = Utilview.setupAdapter(activity,SpinnerTo,sfrom,"Select Warehouse (To)");
        SpinnerTo.setAdapter(adTo);

        return view;
    }

    @OnClick({R.id.Ed_date,R.id.Submit})
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.Ed_date:
                getDate();
                break;

            case R.id.Submit:
                Toast.makeText(activity,"Please Fill Data...",Toast.LENGTH_SHORT).show();
                break;

            default: break;
        }
    }


    private void getDate()
    {
        edDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(activity,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
                //purchasedate = year+"-"+month+"-"+day;

                //Log.e("DATE SELECTED",""+purchasedate);
            }
        });
    }
}
