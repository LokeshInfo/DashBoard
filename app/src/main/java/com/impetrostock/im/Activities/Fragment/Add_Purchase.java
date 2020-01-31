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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Add_Purchase extends Fragment
{
    @BindView(R.id.EdPrefnum)
    EditText edRefrenceNum;
    @BindView(R.id.EdPdate)
    EditText edPurchaseDate;
    @BindView(R.id.SpPSupplier)
    Spinner SpinnerSupplier;
    @BindView(R.id.EdTotalPay)
    EditText edTotalPay;
    @BindView(R.id.EdPpaidamt)
    EditText edPaidAmt;
    @BindView(R.id.SpPayTyp)
    Spinner SpinnerPayTyp;
    @BindView(R.id.AddPurchase)
    Button AddPurchase;

    @BindView(R.id.selectPaytyp)
    TextView SelPayTyp;
    @BindView(R.id.payid)
    EditText edpayid;

    @BindView(R.id.edPdname)
    EditText edPdName;
    @BindView(R.id.edPdCode)
    EditText edPdCode;
    @BindView(R.id.edPdQuantity)
    EditText edQuantity;
    @BindView(R.id.edPdUnitCost)
    EditText edUnitCost;
    @BindView(R.id.edPdColor)
    EditText edColor;
    @BindView(R.id.edPdsize)
    EditText edSize;

    String stypnm;

    DatePickerDialog picker;


    String payment;
    String suplier;

    String ssuplier[] = {"Impetrosys(C)"};
    ArrayList<String> spaytyp = new ArrayList<>();


    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Add_Purchase add_purchase;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        add_purchase = this;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_purchase,container,false);

        ButterKnife.bind(this,view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("Add Purchase");
        if (((Main_Activity)getActivity()).screenNav())
        {
        ((Main_Activity)getActivity()).lockDrawer();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilview.hidekeyboard(getActivity());
                getActivity().onBackPressed();
            }
        });}

        else
        {   toolbar.setNavigationIcon(R.drawable.menu1);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utilview.hidekeyboard(getActivity());
                    ((Main_Activity)getActivity()).openDrawer();
                }
            });    }

        setupview();
        getDate();

        return view;
    }

    public void setupview()
    {
        spaytyp.add("Cash");
        spaytyp.add("Cheque");
        spaytyp.add("Transfer");

        ArrayAdapter<String> adsupplier = Utilview.setupAdapter(activity,SpinnerSupplier,ssuplier,"Select Supplier");
        SpinnerSupplier.setAdapter(adsupplier);
        suplier = SpinnerSupplier.getSelectedItem().toString();
        Log.e("Spinner : ",""+suplier);

        ArrayAdapter<String> adpaytyp = Utilview.setupAdapter(activity,SpinnerPayTyp,spaytyp.toArray(new String[spaytyp.size()]),"Select Payment Type");
        SpinnerPayTyp.setAdapter(adpaytyp);
        int ptyp = spaytyp.indexOf(stypnm);
        SpinnerPayTyp.setSelection(ptyp+1);
        payment = SpinnerPayTyp.getSelectedItem().toString();
        Log.e("Spinner Payment Type : "," "+payment);

        SpinnerPayTyp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_item = adapterView.getItemAtPosition(i).toString();
                Log.e("Select item : "," "+selected_item);
                if (selected_item.equals("Cheque"))
                {
                    SelPayTyp.setVisibility(View.VISIBLE);
                    SelPayTyp.setText(" Cheque No.");
                    edpayid.setVisibility(View.VISIBLE);
                }

                else if (selected_item.equals("Transfer"))
                {
                    SelPayTyp.setVisibility(View.VISIBLE);
                    SelPayTyp.setText(" Transaction ID");
                    edpayid.setVisibility(View.VISIBLE);
                }
                else
                {
                    SelPayTyp.setVisibility(View.GONE);
                    edpayid.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @OnClick({R.id.EdPdate,R.id.AddPurchase})
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.EdPdate:
                getDate();
                break;

            case R.id.AddPurchase:
                Toast.makeText(activity,"Please Fill Data...",Toast.LENGTH_SHORT).show();

                suplier = SpinnerSupplier.getSelectedItem().toString();
                payment = SpinnerPayTyp.getSelectedItem().toString();
                break;

            default: break;
        }
    }

    private void getDate()
    {
        edPurchaseDate.setOnClickListener(new View.OnClickListener() {
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
                                edPurchaseDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
                //purchasedate = year+"-"+month+"-"+day;

                //Log.e("DATE SELECTED",""+purchasedate);
            }
        });
    }
}
