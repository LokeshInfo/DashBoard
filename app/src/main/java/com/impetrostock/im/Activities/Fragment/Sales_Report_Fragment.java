package com.impetrostock.im.Activities.Fragment;

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

import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.Activities.Fragment2.SSales_Reports;
import com.impetrostock.im.R;
import com.impetrostock.im.utility.Utilview;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Sales_Report_Fragment extends Fragment
{

    @BindView(R.id.EdStartDate)
    EditText ed_startDate;

    @BindView(R.id.EdEndDate)
    EditText ed_endDate;

    @BindView(R.id.SpCreatedBy)
    Spinner SpinnerCreated;

    @BindView(R.id.SpBiller)
    Spinner SpinnerBiller;

    @BindView(R.id.SpCustomer)
    Spinner SpinnerCustomer;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    DatePickerDialog picker;
    Sales_Report_Fragment sales_report_fragment;

    String screated[] = {"test","sample"};
    String scustomer[] = {"Test Customer"};
    String sbiller[] = {"Impetrosys(C)"};

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        sales_report_fragment = this;     }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sale_report,container,false);


        ButterKnife.bind(this,view);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);


        txToolbar.setText("Sale Reports");
        toolbar.setNavigationIcon(R.drawable.menu1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilview.hidekeyboard(getActivity());
                ((Main_Activity)getActivity()).openDrawer();
            }
        });

        setUpView();
        getDate();

        return view;
    }

    private void setUpView()
    {
        ArrayAdapter<String> adCreator = Utilview.setupAdapter(activity,SpinnerCreated,screated,"Created By");
        SpinnerCreated.setAdapter(adCreator);

        ArrayAdapter<String> adCustomer = Utilview.setupAdapter(activity,SpinnerCustomer,scustomer,"Select Customer");
        SpinnerCustomer.setAdapter(adCustomer);

        ArrayAdapter<String> adbill = Utilview.setupAdapter(activity,SpinnerBiller,sbiller,"Select Supplier");
        SpinnerBiller.setAdapter(adbill);
    }

    @OnClick({R.id.EdStartDate,R.id.EdEndDate,R.id.AddSaleReport})
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.EdStartDate:
                getDate();
                break;

            case R.id.EdEndDate:
                getDate();
                break;

            case R.id.AddSaleReport:
                SSales_Reports ss_reports = new SSales_Reports();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,ss_reports).addToBackStack("Add_Reports").commit();

                //Toast.makeText(activity,"Please Fill Data...",Toast.LENGTH_SHORT).show();
                break;

            default: break;
        }
    }


    private void getDate()
    {
        ed_startDate.setOnClickListener(new View.OnClickListener() {
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
                                ed_startDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
                //purchasedate = year+"-"+month+"-"+day;

                //Log.e("DATE SELECTED",""+purchasedate);
            }
        });

        ed_endDate.setOnClickListener(new View.OnClickListener() {
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
                                ed_endDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
                //purchasedate = year+"-"+month+"-"+day;

                //Log.e("DATE SELECTED",""+purchasedate);
            }
        });
    }
}
