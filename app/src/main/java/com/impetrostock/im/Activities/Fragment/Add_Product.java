package com.impetrostock.im.Activities.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.impetrostock.im.Model.Variants_Data;
import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.Activities.Adapter.Products_Adapter;
import com.impetrostock.im.Model.Product_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.utility.Utilview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Add_Product extends Fragment {
    @BindView(R.id.EdPcode)
    EditText Pdcode;
    @BindView(R.id.EdPname)
    EditText PdName;
    @BindView(R.id.EdPprice)
    EditText PdPrice;
    @BindView(R.id.EdPcost)
    EditText PdCost;
    @BindView(R.id.EdPqty)
    EditText PdQuantity;
    @BindView(R.id.AddButton)
    Button AddButton;

    @BindView(R.id.SpCategory)
    Spinner Category;
    @BindView(R.id.SpDiscount)
    Spinner Discount;
    @BindView(R.id.SpPtype)
    Spinner Type;
    @BindView(R.id.SpSubCategory)
    Spinner SubCategory;
    @BindView(R.id.SpShowWeb)
    Spinner ShowWeb;

    @BindView(R.id.LineLayout)
    LinearLayout linerlyt;

    @BindView(R.id.chipGroup)
    ChipGroup chipGroup;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Add_Product add_product;
    Dialog dialog;
    ListView listView;
    Button btndialog;
    private LinearLayout ExLayout;

    private String pname, pid, pprice, pcost, pcat;
    private int pqty = 0;
    String[] cat = {"cat"};
    String[] subcat = {"Select an Option"};
    String[] dis = {"No Discount", "2%", "5%", "10%", "15%", "25%", "50%", "60%"};
    String[] typ = {"New Arrival", "Featured", "Best Sellers", "Special Products"};
    String[] sweb = {"Yes", "No"};
    String[] arr = {"Android", "Ios", "Web", "UI/UX"};
    SListAdapter arrayAdapter;
    List<Variants_Data> variants = new ArrayList<>();
    List<String> sh_variants = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
        add_product = this;               }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_product, container, false);
        ButterKnife.bind(this, view);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);
        ExLayout = (LinearLayout) view.findViewById(R.id.ExLayout);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((Main_Activity) getActivity()).lockDrawer();

        txToolbar.setText("Add Product");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilview.hidekeyboard(getActivity());
                getActivity().onBackPressed();
            }
        });

        setupView();

        variants.add(new Variants_Data("Android",false));
        variants.add(new Variants_Data("Ios",false));
        variants.add(new Variants_Data("Web",false));
        variants.add(new Variants_Data("Seo",false));
        variants.add(new Variants_Data("UI/UX",false));

        arrayAdapter = new SListAdapter();
        initDialog();

        linerlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter.notifyDataSetChanged();
                dialog.show();
              }
        });
        return view;
    }

    private void setupView()
    {
        ArrayAdapter<String> spcat = Utilview.setupAdapter(activity, Category, cat, "Select Category");
        Category.setAdapter(spcat);

        ArrayAdapter<String> spsubcat = Utilview.setupAdapter(activity, SubCategory, subcat, "Select Sub Category");
        SubCategory.setAdapter(spsubcat);

        ArrayAdapter<String> spdis = Utilview.setupAdapter(activity, Discount, dis, "Select Product Discount");
        Discount.setAdapter(spdis);

        ArrayAdapter<String> sptyp = Utilview.setupAdapter(activity, Type, typ, "Select Product Type");
        Type.setAdapter(sptyp);

        ArrayAdapter<String> swb = Utilview.setupAdapter(activity, ShowWeb, sweb, "Select Product Web");
        ShowWeb.setAdapter(swb);
    }

    @OnClick(R.id.AddButton)
    public void OnClick(View view) {

        List<Product_Data> dList = new ArrayList<>();

        pname = PdName.getText().toString().trim();
        pprice = PdPrice.getText().toString().trim();
        pcost = PdCost.getText().toString().trim();

        String sqty = PdQuantity.getText().toString().trim();

        if (sqty.isEmpty()) {
            PdQuantity.setError("Required Field.");    }
        else {
            pqty = Integer.parseInt(sqty);             }

        if (pname.isEmpty() || pid.isEmpty() || pprice.isEmpty() || pcost.isEmpty() || pcat.isEmpty()) {
            Toast.makeText(activity, "Please fill Data Correctly", Toast.LENGTH_SHORT).show();
        } else {
            Product_Data Dob = new Product_Data("'" + pname + "'", "'" + pid + "'", "'" + pprice + "'",
                    "'" + pcost + "'", '"' + pqty + '"', "'" + pcat + "'");

            dList.add(Dob);

            Products_Adapter adapter = new Products_Adapter(activity, dList, null);
            adapter.notifyDataSetChanged();

            SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
            pDialog.setTitleText("Product Added.");
            pDialog.setContentText("New Product Added");
            pDialog.show();

            PdName.setText("");
            PdPrice.setText("");
            PdCost.setText("");
            PdQuantity.setText("");
            PdName.requestFocus();
        }
    }

    private void initDialog() {
        dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.list_dialog);

        btndialog = (Button) dialog.findViewById(R.id.btndialog);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);

        listView = (ListView) dialog.findViewById(R.id.listview);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setSelector(R.color.lightGrey);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Variants_Data vob = variants.get(position);

                vob.setHlite(true);
                view.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                setTag(variants.get(position).getVarName());
            }
        });
    }

    private void setTag(final String tagName)
    {
        Boolean bl=false;

        for (int u=0;u<sh_variants.size();u++)
        {   if (sh_variants.get(u).equals(tagName))
            {       bl=true;                  }}

        if (!bl)
        {
        sh_variants.add(tagName);

        AdField(tagName);

        final Chip chip = new Chip(activity);
           int paddingDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics() );
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
        chip.setChecked(true);
        chip.setText(tagName);
        chip.setCloseIconResource(R.drawable.ic_close_black_24dp);
        chip.setCloseIconEnabled(true);
        //Added click listener on close icon to remove tag from ChipGroup
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sh_variants.remove(tagName);

                AdField(tagName);
                RemoveField(tagName);

                for (int a=0 ;a<variants.size(); a++)
                {
                    if (tagName.equals(variants.get(a).getVarName()))
                    {   Variants_Data vob = variants.get(a);
                        vob.setHlite(false);
                        arrayAdapter.notifyDataSetChanged();   }
                }
                Log.e("Removed : ",""+tagName+"   Chip Id is "+chip.getId());
                chipGroup.removeView(chip);
            }
        });
        chipGroup.addView(chip);}
        else
        { }
    }

    private void AdField(String Fname)
    {
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.ad_field_txview,null);
        ExLayout.addView(rowView,ExLayout.getChildCount());
        TextView txvu = rowView.findViewById(R.id.tx_extr);
        txvu.setTag(Fname);
        txvu.setText(Fname);

        /*else
        {
            TextView tx = rowView.findViewById(R.id.tx_extr);
            String nm = tx.getText().toString();
            ExLayout.removeView(tx);
        }*/
    }

    private void RemoveField(String Fnm)
    {
        int childcount = ExLayout.getChildCount();

        for(int a=0 ; a<childcount ; a++) {

            View element = ExLayout.getChildAt(a);

                TextView tx = (TextView)element.findViewById(R.id.tx_extr);
                String tagNM = tx.getText().toString().trim();
                Log.e("Name Delete : ",""+tagNM);
                if (Fnm.equals(tagNM)) {
                ((LinearLayout) element.getParent()).removeView(element);
            }
        }
    }

    class SListAdapter extends ArrayAdapter
    {
        SListAdapter()
        {
            super(activity,R.layout.dialog_list_item,variants);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final Variants_Data sob =   variants.get(position);

            LayoutInflater inflater =getLayoutInflater();
            View itemview =inflater.inflate(R.layout.dialog_list_item,null);

            if (sob.getHlite())
            {   itemview.setBackgroundColor(getResources().getColor(R.color.lightGrey));      }
            else
            {   itemview.setBackgroundColor(getResources().getColor(R.color.white));          }

            TextView t1 = itemview.findViewById(R.id.Tv_Name);
            t1.setText(sob.getVarName());

            return itemview;
        }
    }
}

//////
    /*private void setTag(final List<String> tagList)
    {
        for (int index = 0; index < tagList.size(); index++) {
            final String tagName = tagList.get(index);
            final Chip chip = new Chip(activity);
            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics()
            );
            chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
            chip.setChecked(true);
            chip.setText(tagName);
            chip.setCloseIconResource(R.drawable.ic_close_black_24dp);
            chip.setCloseIconEnabled(true);
            //Added click listener on close icon to remove tag from ChipGroup
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tagList.remove(tagName);
                    chipGroup.removeView(chip);
                }
            });
            chipGroup.addView(chip);
        }
    }*/