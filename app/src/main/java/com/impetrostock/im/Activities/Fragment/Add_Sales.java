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
import android.widget.Button;
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

public class Add_Sales extends Fragment
{

    @BindView(R.id.Eddate)
    EditText eddate;
    @BindView(R.id.EdPrefnum)
    EditText edRefno;
    @BindView(R.id.SpBiller)
    Spinner SpinnerBiller;
    @BindView(R.id.SpCustomer)
    Spinner SpinnerCustomer;
    @BindView(R.id.SpInvoiceTax)
    Spinner SpinnerInvoiceTax;
    @BindView(R.id.EdPdNm)
    EditText edPdname;
    @BindView(R.id.edPdCode)
    EditText edPCode;
    @BindView(R.id.edDiscount)
    EditText edDiscount;
    @BindView(R.id.edTaxRate)
    EditText edTaxRate;
    @BindView(R.id.edSize)
    EditText edSize;
    @BindView(R.id.edPdColor)
    EditText edPColor;
    @BindView(R.id.edQuantity)
    EditText edQuantity;
    @BindView(R.id.edUnitPrice)
    EditText edUnitPrice;
    @BindView(R.id.ed_Note)
    EditText edNote;
    @BindView(R.id.AddSale)
    Button AddSale;


    DatePickerDialog picker;

    String spbill[] = {"Anil"};
    String spcustomer[] = {"Test Customer(P)"};
    String spintax[] = {"No Tax","CGST","IGST","SGST"};

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Add_Sales add_sales;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        add_sales = this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_sales,container,false);
        ButterKnife.bind(this,view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);


        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((Main_Activity)getActivity()).lockDrawer();

        txToolbar.setText("Add Sales");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilview.hidekeyboard(getActivity());
                getActivity().onBackPressed();
            }
        });

        setUpView();
        getDate();

        return view;
    }


    @OnClick({R.id.Eddate,R.id.AddSale})
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.Eddate:
                getDate();
                break;

            case R.id.AddSale:
                Toast.makeText(activity,"Please Fill Date...",Toast.LENGTH_SHORT).show();
                break;
            default: break;
        }

    }


    private void setUpView()
    {
        ArrayAdapter<String> adbill = Utilview.setupAdapter(activity,SpinnerBiller,spbill,"Select Biller");
        SpinnerBiller.setAdapter(adbill);

        ArrayAdapter<String> adcus = Utilview.setupAdapter(activity,SpinnerCustomer,spcustomer,"Select Customer");
        SpinnerCustomer.setAdapter(adcus);

        ArrayAdapter<String> adtax = Utilview.setupAdapter(activity,SpinnerInvoiceTax,spintax,"Invoice Tax");
        SpinnerInvoiceTax.setAdapter(adtax);

    }


    private void getDate()
    {
        eddate.setOnClickListener(new View.OnClickListener() {
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
                                eddate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
                //purchasedate = year+"-"+month+"-"+day;

                //Log.e("DATE SELECTED",""+purchasedate);
            }
        });
    }


}
