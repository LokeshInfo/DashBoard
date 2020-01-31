package com.impetrostock.im.Activities.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.R;
import com.impetrostock.im.utility.Utilview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Update_Product_Price_fragment extends Fragment
{
    @BindView(R.id.SelectFile)
    Button SelectFile;
    @BindView(R.id.UpdateButton)
    Button UpdatePrice;
    @BindView(R.id.filenm)
    TextView FileName;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Update_Product_Price_fragment update_product_price_fragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        update_product_price_fragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.update_product_fragment,container,false);

        ButterKnife.bind(this,view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("Update Product Price");
        toolbar.setNavigationIcon(R.drawable.menu1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity)getActivity()).openDrawer();
            }
        });

        return view;
    }

    @OnClick({R.id.SelectFile,R.id.UpdateButton})
    public void Clicck(View v)
    {
     switch (v.getId())
     {
         case R.id.SelectFile:
             selFile();
             break;

         case R.id.UpdateButton:
             break;

     }
    }

    public void selFile()
    {
        Intent uploadIntent = new Intent();
        uploadIntent.setType("text/*");
        uploadIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(uploadIntent,1001);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1001:
                if (data!=null)
                {
                Uri selected_File = data.getData();
                String stri = Utilview.getRealPathFromURI(selected_File,activity);
                String  F_Name= stri.substring(stri.lastIndexOf("/")+1);
                FileName.setText(""+F_Name);
                }

            break;
        }
    }
}
