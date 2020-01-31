package com.impetrostock.im.Activities.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.impetrostock.im.Model.Billers_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;


import java.util.List;

public class Billers_Adapter extends RecyclerView.Adapter<Billers_Adapter.ViewHolder>
{
    private List<Billers_Data> dataList;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public Billers_Adapter(Activity mActivity, List<Billers_Data> dataList, OnItemTouchListener onItemTouchListener)
    {
        this.mActivity = mActivity;
        this.dataList = dataList;
        this.onItemTouchListener = onItemTouchListener;
    }

    @NonNull
    @Override
    public Billers_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_billers_list,viewGroup,false);
        return new Billers_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Billers_Adapter.ViewHolder holder, int position) {
        if (holder!=null)
        {
            Billers_Data bob = dataList.get(position);
            holder.aName.setText("Name : "+bob.getName());
            holder.Company.setText("Company : "+bob.getCompany());
            holder.Phone.setText("Phone : "+bob.getPhone());
            holder.Email.setText("Email : "+bob.getEmail());
            holder.City.setText("City : "+bob.getCity());
            holder.Country.setText("Country : "+bob.getCountry());
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView aName;
        TextView Company;
        TextView Phone;
        TextView Email;
        TextView City;
        TextView Country;

        public ViewHolder(View itemview)
        {
            super(itemview);
            aName = itemview.findViewById(R.id.tx_name);
            Company = itemview.findViewById(R.id.tx_company);
            Phone = itemview.findViewById(R.id.tx_phone);
            Email = itemview.findViewById(R.id.tx_Email);
            City = itemview.findViewById(R.id.tx_City);
            Country = itemview.findViewById(R.id.tx_Country);
        }
    }
}
